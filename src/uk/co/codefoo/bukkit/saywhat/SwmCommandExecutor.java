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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length < 2)
        {
            return false;
        }

        Player currentPlayer = sender instanceof Player ? (Player) sender : null;

        String abbreviationKey = args[0].toLowerCase();
        String expandedMessage = sayWhat.getMessageExpander().getExpandedMessage(abbreviationKey, currentPlayer, true);

        HashSet<Player> recipientList = getRecipientList(currentPlayer, args);

        sendMessageToRecipients(recipientList, expandedMessage);

        sendMessageToSubscribedPlugins(currentPlayer, expandedMessage, recipientList);

        sayWhat.saveQuickMessageRecipientListForPlayer(currentPlayer, recipientList);

        feedbackResultToCommandSender(currentPlayer, expandedMessage, recipientList);

        return true;
    }

    private HashSet<Player> getRecipientList(Player currentPlayer, String[] args)
    {
        HashSet<Player> recipientList;

        int lengthOfArgumentsWhenNoRecipientListGiven = 1;
        if (args.length == lengthOfArgumentsWhenNoRecipientListGiven)
        {
            recipientList = sayWhat.getQuickMessageRecipientListForPlayer(currentPlayer);
            return recipientList;
        }

        recipientList = new HashSet<Player>();
        for (int i = 1; i < args.length; i++)
        {
            Player currentRecipient = Bukkit.getPlayer(args[i]);
            if (currentRecipient == null)
            {
                Logging.logReply(SayWhat.PluginId, "Not found");
                continue;
            }
            recipientList.add(currentRecipient);
        }
        return recipientList;
    }

    private void sendMessageToRecipients(HashSet<Player> recipientList, String expandedMessage)
    {
        for (Player recipient : recipientList)
        {
            recipient.sendMessage(expandedMessage);
        }
    }

    private void sendMessageToSubscribedPlugins(
            Player currentPlayer, String expandedMessage, HashSet<Player> recipientList)
    {
        if (currentPlayer == null)
        {
            return;
        }
        AsyncPlayerChatEvent chatEvent = new AsyncPlayerChatEvent(
                false
                ,currentPlayer
                ,expandedMessage
                ,recipientList);

        Bukkit.getServer().getPluginManager().callEvent(chatEvent);
    }

    private void storePlayerRecipientListForCommandSender(Player currentPlayer, HashSet<Player> recipientList)
    {
    }

    private void feedbackResultToCommandSender(
            Player currentPlayer, String expandedMessage, HashSet<Player> recipientList)
    {
        // Feedback result to command sender
        int recipients = recipientList.size();
        String resultMessage = String.format(
                "[swm sent '%s' to %d player%s]",
                expandedMessage,
                recipients,
                recipients == 1 ? "" : "s"
        );
        Logging.logReply(SayWhat.PluginId, resultMessage, currentPlayer);
    }
}
