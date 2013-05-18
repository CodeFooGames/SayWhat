package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import uk.co.codefoo.bukkit.util.Logging;

public class SwlCommandExecutor implements CommandExecutor
{
    private SayWhat sayWhat;

    public SwlCommandExecutor(SayWhat sayWhat)
    {
        this.sayWhat = sayWhat;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        String result;
        if (args.length > 1)
        {
            return false;
        }

        if (args.length == 0)
        {
            result = sayWhat.getAbbreviations().getAllAbbreviationKeys();
            Logging.logReply(SayWhat.PluginId, result, sender);
            return true;
        }
        
        String abbreviationKey = args[0].toLowerCase();
        result = sayWhat.getAbbreviations().getAbbreviationDescription(abbreviationKey);
        Logging.logReply(SayWhat.PluginId, result, sender);

        return true;
    }

}
