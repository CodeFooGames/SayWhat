package uk.co.codefoo.bukkit.saywhat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SwsaveCommandExecutor implements CommandExecutor
{
    private SayWhat sayWhat;

    public SwsaveCommandExecutor(SayWhat sayWhat)
    {
        this.sayWhat = sayWhat;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        return args.length == 0 && sayWhat.saveToConfig();
    }

}
