package net.warvale.core.message;

import net.warvale.core.spec.Preferences;
import net.warvale.staffcore.users.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import java.util.HashMap;


/**
 * Created By AAces on 6/1/17
 */
public class PrivateMessages implements CommandExecutor {


    public static HashMap<String, Player> lastMessaged = new HashMap<>();

    public boolean onCommand(CommandSender player, Command command, String label, String[] args){
        if (label.equalsIgnoreCase("msg")) {
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

        }
        return false;
    }

}
