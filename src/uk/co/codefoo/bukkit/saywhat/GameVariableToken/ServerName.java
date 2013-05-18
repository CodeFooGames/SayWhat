package uk.co.codefoo.bukkit.saywhat.GameVariableToken;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerName implements TokenExpander
{
	private Token token;
	public ServerName()
	{
		token = new Token(
			"servername"
			,"The name of this Minecraft server"
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
		String result = Bukkit.getServer().getServerName();
		return result;
	}
}
