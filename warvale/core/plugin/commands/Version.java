package warvale.core.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Version implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gamever")) {
            sender.sendMessage(ChatColor.GRAY + "Gamecore is running on version 1.14");
            return true;
        }
        return false;
    }

}
