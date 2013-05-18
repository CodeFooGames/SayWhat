package uk.co.codefoo.bukkit.saywhat.GameVariableToken;

import org.bukkit.entity.Player;


public interface TokenExpander
{
	Token getToken();
	String getGameVariableValue(Player currentPlayer);
}
