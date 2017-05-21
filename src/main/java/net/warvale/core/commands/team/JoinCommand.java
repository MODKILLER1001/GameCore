package net.warvale.core.commands.team;


import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class JoinCommand extends AbstractCommand {

    public JoinCommand() {
        super("join", "<red|blue>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (args.length == 0) {
            return false;
        }

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        Player player = (Player) sender;
        String team = args[0];

        if (team.equalsIgnoreCase("blue")) {
            if (Main.getTeams().getBlueTeam().getEntries().contains(player.getName())) {
                throw new CommandException(ChatColor.GRAY + "You're already on the " + ChatColor.DARK_AQUA + "blue team");
            } else {
                // Join the team
                if (Main.getTeams().getRedTeam().getEntries().contains(player.getName())) {
                    throw new CommandException(ChatColor.GRAY + "You may not change teams at this time.");
                }
                Main.getTeams().getBlueTeam().addEntry(player.getName());
                player.sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
                return true;
            }
        }

        if (team.equalsIgnoreCase("red")) {
            if (Main.getTeams().getRedTeam().getEntries().contains(player.getName())) {
                throw new CommandException(ChatColor.GRAY + "You're already on the " + ChatColor.RED + "red team");
            } else {
                // Join the team
                if (Main.getTeams().getBlueTeam().getEntries().contains(player.getName())) {
                    throw new CommandException(ChatColor.GRAY + "You may not change teams at this time.");
                }
                Main.getTeams().getRedTeam().addEntry(player.getName());
                sender.sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
                return true;
            }
        }

        if (team.equalsIgnoreCase("spectator")) {
            if (Main.getTeams().getSpectatorTeam().getEntries().contains(player.getName())) {
                throw new CommandException(ChatColor.GRAY + "You're already spectating!");
            } else {
                // Join the team
                if (!player.hasPermission("warvale.mod")) {
                    throw new CommandException(ChatColor.GRAY + "You may not spectate at this time.");
                }

                return true;
            }
        }


        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> toReturn = new ArrayList<>();

        if (args.length == 1) {
            toReturn.add("red");
            toReturn.add("blue");
            toReturn.add("spectator");
        }

        return toReturn;
    }

}
