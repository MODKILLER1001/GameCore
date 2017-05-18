package warvale.core.plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Classes implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("classes")) {
            sender.sendMessage(ChatColor.GRAY + "Available classes: Soldier, Archer, Assassin, Miner, Spy, Technician, Musician, Pyromaniac, Necromancer, Earthbender, Medic");
            return true;
        }
        return false;
    }

}
