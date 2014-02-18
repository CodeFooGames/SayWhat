package uk.co.codefoo.bukkit.saywhat.GameVariableToken;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Plugins implements TokenExpander
{
	private Token token;
	public Plugins()
	{
		token = new Token(
			"plugins"
			,"A list of plugins running on this server"
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
		Plugin[] plugins = Bukkit.getServer().getPluginManager().getPlugins();
        StringBuilder pluginsListBuilder = new StringBuilder();
        int numberOfPluginsListed = 0;
        for (Plugin plugin : plugins)
        {
            pluginsListBuilder.append(plugin.getName());
            numberOfPluginsListed++;
            if (numberOfPluginsListed == plugins.length)
            {
                continue;
            }
            pluginsListBuilder.append(", ");
        }

        return pluginsListBuilder.toString();
	}
}
