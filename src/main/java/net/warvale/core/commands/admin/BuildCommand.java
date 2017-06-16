package net.warvale.core.commands.admin;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 6/5/2017.
 */
public class BuildCommand extends AbstractCommand {
    public static ArrayList<Player> canBuild = new ArrayList<>();
    public BuildCommand() {
        super("allowbuild", "");
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
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
    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
