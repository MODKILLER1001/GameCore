package net.warvale.core.chat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by AAces on 6/10/2017.
 */
public class ChatNameColorCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("chatnamecolor")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by players");
                return false;
            }
            Player player = (Player) sender;
            ChatNameColorGUI.colorGUI(player);
            return true;
        }
    return false;
    }
}