package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import uk.co.codefoo.bukkit.util.Logging;

public class SwrCommandExecutor implements CommandExecutor
{
    private SayWhat sayWhat;

    public SwrCommandExecutor(SayWhat sayWhat)
    {
        this.sayWhat = sayWhat;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        if (args.length < 2)
        {
            return false;
        }

        String abbreviationKey = args[0].toLowerCase();

        StringBuilder abbreviationBuilder = new StringBuilder();
        for (int i=1; i<args.length; i++)
        {
            if (i > 1)
            {
                abbreviationBuilder.append(' ');
            }
            abbreviationBuilder.append(args[i]);
        }
        String abbreviationValue = abbreviationBuilder.toString();
        
        boolean didReplace = sayWhat.getAbbreviations().put(abbreviationKey, abbreviationValue);

        String result = String.format(
                "%s '%s' with the message '%s'.", 
                didReplace ? "Replaced" : "Created",
                abbreviationKey, 
                abbreviationValue);
        Logging.logReply(SayWhat.PluginId, result, sender);

        return true;
    }

}
