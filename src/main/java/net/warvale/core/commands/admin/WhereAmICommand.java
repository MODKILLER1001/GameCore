package net.warvale.core.commands.admin;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 6/21/2017.
 */
public class WhereAmICommand extends AbstractCommand{
    public WhereAmICommand() {
        super("whereami", "");
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            String world = player.getLocation().getWorld().getName();
            String x = String.valueOf(player.getLocation().getX());
            String y = String.valueOf(player.getLocation().getY());
            String z = String.valueOf(player.getLocation().getZ());
            String yaw = String.valueOf(player.getLocation().getYaw());
            String pitch = String.valueOf(player.getLocation().getPitch());
            player.sendMessage(ChatColor.GOLD + "World: " + ChatColor.RED + world + "\n" + ChatColor.GOLD + "X: " + ChatColor.RED + x + "\n" + ChatColor.GOLD + "Y: " + ChatColor.RED + y + "\n" + ChatColor.GOLD + "Z: " + ChatColor.RED + z + "\n" + ChatColor.GOLD + "Yaw: " + ChatColor.RED + yaw + "\n" + ChatColor.GOLD + "Pitch: " + ChatColor.RED + pitch + "\n");
            return true;
        } else {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
