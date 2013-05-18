package uk.co.codefoo.bukkit.saywhat.GameVariableToken;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MOTD implements TokenExpander
{
	private Token token;
	public MOTD()
	{
		token = new Token(
			"motd"
			,"The message of the day"
		);
	}
	@Override
	public Token getToken()
	{
		return token;
	}

	@Override
	public String getGameVariableValue(Player currentPlayer)
	{
		String result =  Bukkit.getServer().getMotd();
		return result;
	}
}
