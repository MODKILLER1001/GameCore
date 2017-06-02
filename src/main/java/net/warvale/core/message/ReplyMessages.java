package net.warvale.core.message;

import net.warvale.core.spec.Preferences;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * Created By AAces on 6/1/17
 */
public class ReplyMessages implements CommandExecutor {

    public boolean onCommand(CommandSender player, Command command, String label, String[] args){
        if (label.equalsIgnoreCase("r")) {
            if (!(player instanceof Player)){
                player.sendMessage("This command can only be run by players");
                return false;
            }

            Player sender = (Player) player;

            if (!PrivateMessages.lastMessaged.containsKey(sender.getName())){
                sender.sendMessage(ChatColor.RED + "You have not messaged anyone recently!");
                return false;
            }

            if (args.length == 0){
                player.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Usage: " + ChatColor.RED + "/r <message>");
                return false;
            }
            Player t = PrivateMessages.lastMessaged.get(sender.getName());

            if (!Bukkit.getServer().getOnlinePlayers().contains(t)){
                sender.sendMessage(ChatColor.RED + PrivateMessages.lastMessaged.get(sender.getName()).getName() + ChatColor.DARK_GRAY + " could not be found!");
                return false;
            }

            String message = "";

            for (int i = 0; i < args.length; i++){
                message = message + " " + args[i];
            }

            for (Player target : Bukkit.getServer().getOnlinePlayers()){
                if (target == t) {
                    if (Preferences.noPrivateMessages.contains(target.getName())) {
                        sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.DARK_GRAY + " has private messages disabled!");
                        return false;
                    } else {
                        target.sendMessage(ChatColor.AQUA + "From " + ChatColor.WHITE + sender.getName() + ChatColor.GRAY + ":" + ChatColor.GRAY + message);
                        sender.sendMessage(ChatColor.AQUA + "To " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + ":" + ChatColor.GRAY + message);
                        PrivateMessages.lastMessaged.put(target.getName(), sender);
                        return true;
                    }
                }
            }

        }
        return false;
    }

}
