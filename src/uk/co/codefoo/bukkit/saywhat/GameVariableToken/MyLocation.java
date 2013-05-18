package uk.co.codefoo.bukkit.saywhat.GameVariableToken;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MyLocation implements TokenExpander
{
	private Token token;
	public MyLocation()
	{
		token = new Token(
			"myloc"
			,"The current player's location"
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
		if (currentPlayer==null)
		{
			return "<console>";
		}
		Location loc = currentPlayer.getLocation();
        return String.format(
            "x:%.0f, z:%.0f, y:%.0f"
            ,loc.getX()
            ,loc.getZ()
            ,loc.getY()
        );
	}
}
