package net.warvale.core.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by AAces on 6/5/2017.
 */
public class BuildCommand implements CommandExecutor {
    public static ArrayList<Player> canBuild = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("allowbuild")) {

            if (args.length == 0) {
                sender.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Usage: " + ChatColor.RED + "/allowbuild <player>");
                return false;
            }

            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatColor.RED + args[0] + ChatColor.DARK_GRAY + " could not be found!");
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (!canBuild.contains(target)){
                canBuild.add(target);
                sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + target.getName() + " now has permission to build!");
                target.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + sender.getName() + " has given you permission to build!");
                return true;
            } else {
                canBuild.remove(target);
                sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + target.getName() + " no longer has permission to build!");
                target.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + sender.getName() + " has taken away your permission to build!");
                return true;
            }
        }
        return false;
    }
}
