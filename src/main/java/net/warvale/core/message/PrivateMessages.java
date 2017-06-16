package net.warvale.core.message;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.spec.Preferences;
import net.warvale.staffcore.users.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created By AAces on 6/1/17
 */
public class PrivateMessages extends AbstractCommand {


    public static HashMap<String, Player> lastMessaged = new HashMap<>();
    public PrivateMessages() {
        super("msg", "");
    }
    @Override
    public boolean execute(CommandSender player, String[] args) throws CommandException {
        if (!(player instanceof Player)){
            player.sendMessage("This command can only be run by players");
            return false;
        }

        Player sender = (Player) player;

        if (args.length == 0 || args.length == 1){
            player.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Usage: " + ChatColor.RED + "/msg <player> <message>");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null){
            sender.sendMessage(ChatColor.RED + args[0] + ChatColor.DARK_GRAY + " could not be found!");
            return false;
        }

        if (sender.getName().equalsIgnoreCase(args[0])){
            sender.sendMessage(ChatColor.RED + "You can't send a message to yourself!");
            return false;
        }

        String message = "";

        for (int i = 1; i < args.length; i++){
            message = message + " " + args[i];
        }

        for (Player target : Bukkit.getServer().getOnlinePlayers()){
            if (target.getName().equalsIgnoreCase(args[0])) {
                if (Preferences.noPrivateMessages.contains(target.getName())) {
                    sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.DARK_GRAY + " has private messages disabled!");
                    return false;
                } else {
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&',ChatColor.AQUA + "From " + UserManager.getUser(sender.getUniqueId()).getPrefix() + ChatColor.WHITE + sender.getName() + ChatColor.GRAY + ":" + ChatColor.GRAY + message));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',ChatColor.AQUA + "To " + UserManager.getUser(target.getUniqueId()).getPrefix() + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ":" + ChatColor.GRAY + message));
                    lastMessaged.put(sender.getName(), target);
                    lastMessaged.put(target.getName(), sender);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }






}