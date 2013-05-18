package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.co.codefoo.bukkit.util.Logging;

public class SwCommandExecutor implements CommandExecutor
{
    private SayWhat sayWhat;

    public SwCommandExecutor(SayWhat sayWhat)
    {
        this.sayWhat = sayWhat;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }

        String abbreviationKey = args[0].toLowerCase();
        String abbreviationValue = sayWhat.getAbbreviations().get(abbreviationKey);

        if (abbreviationValue==null)
        {
            String result = sayWhat.getAbbreviations().getAbbreviationNotFoundText(abbreviationKey);
            Logging.logReply(SayWhat.PluginId, result);
            return true;
        }
        
        if (sender instanceof Player)
        {
            Player currentPlayer = (Player) sender;
            abbreviationValue = sayWhat.getTokenExpanders().expandAllTokens(abbreviationValue, currentPlayer);
            currentPlayer.chat(abbreviationValue);
            return true;
        }
        
        abbreviationValue = sayWhat.getTokenExpanders().expandAllTokens(abbreviationValue, null);
        Bukkit.broadcastMessage(abbreviationValue);
        return true;
    }
}
