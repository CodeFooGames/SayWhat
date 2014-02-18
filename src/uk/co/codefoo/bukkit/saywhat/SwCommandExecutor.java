package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        Player currentPlayer = sender instanceof Player ? (Player) sender : null;

        String abbreviationKey = args[0].toLowerCase();
        String expandedMessage = sayWhat.getMessageExpander().getExpandedMessage(abbreviationKey, currentPlayer, false);

        if (currentPlayer != null)
        {
            currentPlayer.chat(expandedMessage);
            return true;
        }

        Bukkit.broadcastMessage(expandedMessage);
        return true;
    }
}