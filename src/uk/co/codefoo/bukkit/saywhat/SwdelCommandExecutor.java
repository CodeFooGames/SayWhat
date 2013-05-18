package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import uk.co.codefoo.bukkit.util.Logging;

public class SwdelCommandExecutor implements CommandExecutor
{
    private SayWhat sayWhat;

    public SwdelCommandExecutor(SayWhat sayWhat)
    {
        this.sayWhat = sayWhat;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }

        String abbreviationKey = args[0].toLowerCase();

        boolean didRemove = sayWhat.getAbbreviations().remove(abbreviationKey);

        String result;
        if (didRemove)
        {
            result = String.format("Deleted abbreviation '%s'.", abbreviationKey);
        }
        else
        {
            result=String.format("There is no '%s' abbreviation.", abbreviationKey);
        }
    
        Logging.logReply(SayWhat.PluginId, result, sender);
        return true;
    }

}
