package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import uk.co.codefoo.bukkit.saywhat.GameVariableToken.TokenExpanders;
import uk.co.codefoo.bukkit.util.Logging;

public class SwtCommandExecutor implements CommandExecutor
{
    private SayWhat sayWhat;

    public SwtCommandExecutor(SayWhat sayWhat)
    {
        this.sayWhat = sayWhat;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        TokenExpanders tokens = sayWhat.getTokenExpanders();
        String result;
        if (args.length > 1)
        {
            return false;
        }

        if (args.length == 0)
        {
            result=tokens.getAllTokenKeys();
            Logging.logReply(SayWhat.PluginId, result, sender);
            return true;
        }
        
        String tokenKey = args[0];
        result=tokens.getTokenDescription(tokenKey);
        Logging.logReply(SayWhat.PluginId, result, sender);
        return true;
    }
}
