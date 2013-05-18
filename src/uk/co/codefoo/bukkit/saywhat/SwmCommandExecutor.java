package uk.co.codefoo.bukkit.saywhat;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import uk.co.codefoo.bukkit.util.Logging;

public class SwmCommandExecutor implements CommandExecutor
{
    private SayWhat sayWhat;

    public SwmCommandExecutor(SayWhat sayWhat)
    {
        this.sayWhat = sayWhat;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        if (args.length < 2)
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

        Player currentPlayer = null;
        if (sender instanceof Player)
        {
            currentPlayer = (Player) sender;
        }

        abbreviationValue = sayWhat.getTokenExpanders().expandAllTokens(abbreviationValue, currentPlayer);

        String message = abbreviationValue;
        if (currentPlayer != null)
        {
            message = String.format("<%s: %s>",currentPlayer.getDisplayName(),abbreviationValue);
        }

        HashSet<Player> recipientList = new HashSet<Player>();
        for (int i = 1; i < args.length; i++)
        {
            Player currentRecipient = Bukkit.getPlayer(args[i]);
            if (currentRecipient == null)
            {
                Logging.logReply(SayWhat.PluginId, "Not found");
                continue;
            }
            recipientList.add(currentRecipient);
            currentRecipient.sendMessage(message);
        }

        int recipients = recipientList.size();

        String result = String.format(
                "[swm sent '%s' to %d player%s]",
                message,
                recipients,
                recipients == 1 ? "" : "s"
        );
        
        if (currentPlayer != null)
        {
            currentPlayer.sendMessage(result);

            AsyncPlayerChatEvent chatEvent = new AsyncPlayerChatEvent(
                    false
                    ,currentPlayer
                    ,abbreviationValue
                    ,recipientList);
            
            Bukkit.getServer().getPluginManager().callEvent(chatEvent);
            
            return true;
        }

        Bukkit.broadcastMessage(abbreviationValue);
        return true;
    }
}
