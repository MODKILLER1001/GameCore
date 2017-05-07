package warvale.core.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Kits implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kits")) {
            sender.sendMessage(ChatColor.GRAY + "Available kits: Soldier, Hunter");
            return true;
        }
        return false;
    }
   
}
