package uk.co.codefoo.bukkit.saywhat.Message;

import org.bukkit.entity.Player;
import uk.co.codefoo.bukkit.saywhat.Abbreviation.Abbreviations;
import uk.co.codefoo.bukkit.saywhat.GameVariableToken.TokenExpanders;
import uk.co.codefoo.bukkit.saywhat.SayWhat;
import uk.co.codefoo.bukkit.util.Logging;

public class MessageExpander {
    private Abbreviations abbreviations;
    private TokenExpanders tokenExpanders;

    public MessageExpander(Abbreviations abbreviations, TokenExpanders tokenExpanders)
    {
        this.tokenExpanders = tokenExpanders;
        this.abbreviations = abbreviations;
    }
    public String getExpandedMessage(String abbreviationKey, Player currentPlayer, boolean isPrivate)
    {
        String expandedMessage = abbreviations.get(abbreviationKey);

        if (expandedMessage==null)
        {
            String result = abbreviations.getAbbreviationNotFoundText(abbreviationKey);
            Logging.logReply(SayWhat.PluginId, result);
            return result;
        }

        expandedMessage = tokenExpanders.expandAllTokens(expandedMessage, currentPlayer);

        String playerName = currentPlayer == null ? "CONSOLE" : currentPlayer.getDisplayName();

        if (isPrivate)
        {
            expandedMessage = String.format("<%s: %s>", playerName, expandedMessage);
        }

        return expandedMessage;
    }

}
