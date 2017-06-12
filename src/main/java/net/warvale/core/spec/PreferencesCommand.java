package net.warvale.core.spec;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by AAces on 6/10/2017.
 *///TODO: Move to StaffCore
public class PreferencesCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("settings")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by players");
                return false;
            }

            Player player = (Player) sender;
            Preferences.tsGUI(player);
            return true;
        }
        return false;
    }
}