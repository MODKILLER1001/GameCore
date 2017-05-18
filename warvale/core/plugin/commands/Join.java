package warvale.core.plugin.commands;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import warvale.core.plugin.Main;

public class Join implements CommandExecutor {

	public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
		if (cs instanceof Player) {
			Player sender = (Player) cs;

			if (args.length == 1) {
				switch (args[0]) {
				case "blue":
					if (Main.getBlueTeam().getEntries().contains(sender.getName())) {
						sender.sendMessage(
								ChatColor.GRAY + "You're already on the " + ChatColor.DARK_AQUA + "blue team");
						return true;
					} else {
						// Join the team
						if (Main.getRedTeam().getEntries().contains(sender.getName())) {
							sender.sendMessage(ChatColor.GRAY + "You may not change teams at this time.");
							return true;
						}
						Main.getBlueTeam().addEntry(sender.getName());
						sender.sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
						for (PotionEffect effect : sender.getActivePotionEffects())
							sender.removePotionEffect(effect.getType());
						return true;
					}
				case "red":
					if (Main.getRedTeam().getEntries().contains(sender.getName())) {
						sender.sendMessage(ChatColor.GRAY + "You're already on the " + ChatColor.RED + "red team");
						return true;
					} else {
						// Join the team
						if (Main.getBlueTeam().getEntries().contains(sender.getName())) {
							sender.sendMessage(ChatColor.GRAY + "You may not change teams at this time.");
							return true;
						}
						Main.getRedTeam().addEntry(sender.getName());
						sender.sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
						for (PotionEffect effect : sender.getActivePotionEffects())
							sender.removePotionEffect(effect.getType());
						return true;
					}

				case "spectator":
					if (Main.getSpectatorTeam().getEntries().contains(sender.getName())) {
						sender.sendMessage(ChatColor.GRAY + "You're already spectating!");
						return true;
					} else {
						// Join the team
						sender.sendMessage(ChatColor.GRAY + "You may not spectate at this time."); // temporarily
																									// disable
																									// spectating.
						/*
						 * Main.getSpectatorTeam().addEntry(sender.getName());
						 * sender.sendMessage(ChatColor.GRAY +
						 * "You left the game and became a spectator.");
						 * sender.addPotionEffect(new
						 * PotionEffect(PotionEffectType.INVISIBILITY, 100000,
						 * 1)); sender.getInventory().clear();
						 * sender.setAllowFlight(true);
						 */
						return true;
					}
				default:
					sender.sendMessage(ChatColor.GRAY + "Incorrect usage. Correct usage: /join <" + ChatColor.RED
							+ "red" + ChatColor.GRAY + "/" + ChatColor.DARK_AQUA + "blue" + ChatColor.GRAY + ">");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.GRAY + "Incorrect usage. Correct usage: /join <" + ChatColor.RED + "red"
						+ ChatColor.GRAY + "/" + ChatColor.DARK_AQUA + "blue" + ChatColor.GRAY + ">");
				return true;
			}
		}

		return false;
	}

}