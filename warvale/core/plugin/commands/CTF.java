package warvale.core.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CTF implements CommandExecutor {

	public boolean onCommand(CommandSender sender,
            Command command,
            String label,
            String[] args) {
        if (command.getName().equalsIgnoreCase("ctf")) {
            sender.sendMessage(ChatColor.GRAY + "CTFCore working properly on " + ChatColor.GREEN + "version 1.1.2");
            return true;
        }
        return false;
    }
   
}
