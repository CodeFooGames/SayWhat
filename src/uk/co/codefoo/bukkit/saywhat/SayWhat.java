package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.codefoo.bukkit.saywhat.Abbreviation.Abbreviations;
import uk.co.codefoo.bukkit.saywhat.GameVariableToken.TokenExpanders;
import uk.co.codefoo.bukkit.saywhat.Message.MessageExpander;
import uk.co.codefoo.bukkit.util.Logging;

import java.util.HashSet;

public class SayWhat extends JavaPlugin {
	public static final String PluginId = "SayWhat";

    private Abbreviations abbreviations;
    public Abbreviations getAbbreviations()
    {
        return abbreviations;
    }

    private TokenExpanders tokenExpanders;
    public TokenExpanders getTokenExpanders()
    {
        return tokenExpanders;
    }

	private MessageExpander messageExpander;
    public MessageExpander getMessageExpander()
	{
	    return messageExpander;
	}
	
	@Override
	public void onEnable()
	{
		Logging.logReply(
				PluginId
				,"SayWhat from CodeFoo. It bleats so you don't have to."
				,null);

		this.saveDefaultConfig();

        getCommand("sw").setExecutor(new SwCommandExecutor(this));
        getCommand("swdel").setExecutor(new SwdelCommandExecutor(this));
        getCommand("swl").setExecutor(new SwlCommandExecutor(this));
        getCommand("swload").setExecutor(new SwloadCommandExecutor(this));
        getCommand("swm").setExecutor(new SwmCommandExecutor(this));
        getCommand("swr").setExecutor(new SwrCommandExecutor(this));
        getCommand("swsave").setExecutor(new SwsaveCommandExecutor(this));
        getCommand("swt").setExecutor(new SwtCommandExecutor(this));		

        abbreviations = new Abbreviations();
        tokenExpanders = new TokenExpanders();
        messageExpander = new MessageExpander(abbreviations, tokenExpanders);
        
        loadFromConfig();
	}

	@Override
	public void onDisable()
	{
		saveToConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String commandName = cmd.getName().toLowerCase();
		String result = String.format("SayWhat.onCommand: %s not implemented.", commandName);
		Logging.logReply(PluginId, result);
		return true;
	}
	
	public boolean loadFromConfig()
	{
		Logging.logReply(PluginId, "Loading config.");

        return abbreviations.loadFromConfig(getConfig());
	}
	
	public boolean saveToConfig()
	{
		Logging.logReply(PluginId, "Saving config.");

		boolean overallResult = abbreviations.saveToConfig(getConfig());

		if (overallResult)
		{
            this.saveConfig();
		}

		return overallResult;
	}

    public HashSet<Player>  getQuickMessageRecipientListForPlayer(Player currentPlayer)
    {
        return new HashSet<Player>();
    }

    public boolean saveQuickMessageRecipientListForPlayer(Player currentPlayer, HashSet<Player> recipientList)
    {
        return false;
    }
}
