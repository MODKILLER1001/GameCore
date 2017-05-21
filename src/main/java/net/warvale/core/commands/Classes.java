package net.warvale.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Classes implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("classes")) {
            sender.sendMessage(ChatColor.AQUA + "All classes: " + ChatColor.GRAY + "Soldier," + ChatColor.WHITE
                    + " Archer," + ChatColor.GRAY + " Assassin," + ChatColor.WHITE + " Miner," + ChatColor.GRAY
                    + " Spy," + ChatColor.WHITE + " Technician," + ChatColor.GRAY + " Musician," + ChatColor.WHITE
                    + " Pyromaniac," + ChatColor.GRAY + " Necromancer," + ChatColor.WHITE + " Earthbender,"
                    + ChatColor.GRAY + " Medic");
            return true;
        }
        return false;
    }

}
