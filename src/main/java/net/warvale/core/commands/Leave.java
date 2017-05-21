package net.warvale.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;

public class Leave implements CommandExecutor {

    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if (cs instanceof Player) {
            Player sender = (Player) cs;
            if (command.getName().equalsIgnoreCase("leave")) {
                if (Main.getRedTeam().getEntries().contains(sender.getName())) {
                    sender.sendMessage(ChatColor.GRAY + "You may not leave the game at this time.");
                }

                else if (Main.getBlueTeam().getEntries().contains(sender.getName())) {
                    sender.sendMessage(ChatColor.GRAY + "You may not leave the game at this time.");
                }

                else
                    sender.sendMessage(ChatColor.GRAY + "You must be on a team to leave the game.");
                return true;
            }
            return false;
        }
        return false;

    }
}
