package uk.co.codefoo.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Logging {

	public static void logReply(String pluginId, String message)
	{
		logReply(pluginId, message, null);
	}

	public static void logReply(String pluginId, String message, CommandSender sender)
	{
		if (message == null)
		{
			return;
		}

		if (message.length() == 0)
		{
			return;
		}

		String trimmedMessage = message.trim();
		if (trimmedMessage.length() == 0)
		{
			return;
		}
		
		String pluginIdTrimmed = "";
		if (pluginId != null)
		{
			 pluginIdTrimmed = pluginId.trim();
		}
		
		String pluginIdFormatted = "";
		if (pluginIdTrimmed.length() > 0)
		{
			pluginIdFormatted = String.format("[%s]", pluginIdTrimmed);
		}

		String replyMessage = String.format(
				"%s %s"
				,pluginIdFormatted
				,trimmedMessage);

		if (sender == null)
		{
			Bukkit.getLogger().info(replyMessage);
			return;
		}

		if (sender instanceof Player)
		{
			sender.sendMessage(replyMessage);
			return;
		}

		Bukkit.getLogger().info(replyMessage);
	}

}
