package net.warvale.core.commands.team;


import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.game.start.GameStart;
import net.warvale.core.spec.TeamSelect;
import net.warvale.core.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class JoinCommand extends AbstractCommand {

    public JoinCommand() {
        super("join", "<red|blue|auto>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            new TeamSelect(Main.get()).tsGUI(player);
        }
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
                if (GameStart.initActive){
                    player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                }
                if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                    new GameStart().startCountdown();
                }
                return true;
            }
        }

        if (team.equalsIgnoreCase("red")) {
            if (Main.getTeams().getRedTeam().getEntries().contains(player.getName())) {
                throw new CommandException(ChatColor.GRAY + "You're already on the " + ChatColor.RED + "red team");
            } else {
                // Join the team
                if (!player.hasPermission("warvale.teamSelect")){
                    player.sendMessage(net.md_5.bungee.api.ChatColor.RED + "You must have at least a " + net.md_5.bungee.api.ChatColor.DARK_PURPLE + "mythic" + " rank to select a team. Use Auto Join instead.");
                    return false;
                }
                if (Main.getTeams().getRedTeam().getSize() - Main.getTeams().getBlueTeam().getSize() >= 2){
                    player.sendMessage(net.md_5.bungee.api.ChatColor.DARK_GRAY + "[" + net.md_5.bungee.api.ChatColor.DARK_RED + "Warvale" + net.md_5.bungee.api.ChatColor.DARK_GRAY + "] " + net.md_5.bungee.api.ChatColor.GRAY + "This team is full!");
                    return false;
                }
                if (Main.getTeams().getBlueTeam().getEntries().contains(player.getName())) {
                    throw new CommandException(ChatColor.GRAY + "You may not change teams at this time.");
                }
                Main.getTeams().getRedTeam().addEntry(player.getName());
                sender.sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
                if (GameStart.initActive){
                    player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                }
                if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                    new GameStart().startCountdown();
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

        if (team.equalsIgnoreCase("auto")){
            if (Main.getTeams().getBlueTeam().getPlayers().size() > Main.getTeams().getRedTeam().getPlayers().size()){
                //join blue
                Main.getTeams().getBlueTeam().addEntry(player.getName());
                player.sendMessage(net.md_5.bungee.api.ChatColor.GRAY + "You joined team " + net.md_5.bungee.api.ChatColor.DARK_AQUA + "blue");
                for (PotionEffect effect : player.getActivePotionEffects())
                    player.removePotionEffect(effect.getType());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                if (GameStart.initActive){
                    player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                }
                if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                    new GameStart().startCountdown();
                }
            } else if (Main.getTeams().getRedTeam().getPlayers().size() > Main.getTeams().getBlueTeam().getPlayers().size()){
                //join red
                Main.getTeams().getRedTeam().addEntry(player.getName());
                player.sendMessage(net.md_5.bungee.api.ChatColor.GRAY + "You joined team " + net.md_5.bungee.api.ChatColor.RED + "red");
                for (PotionEffect effect : player.getActivePotionEffects())
                    player.removePotionEffect(effect.getType());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                if (GameStart.initActive){
                    player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                }
                if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive) {
                    new GameStart().startCountdown();
                }
            } else if (Main.getTeams().getBlueTeam().getPlayers().size() == Main.getTeams().getRedTeam().getPlayers().size()){
                int rteam = NumberUtils.random(2,1);
                if (rteam == 1){
                    Main.getTeams().getRedTeam().addEntry(player.getName());
                    player.sendMessage(net.md_5.bungee.api.ChatColor.GRAY + "You joined team " + net.md_5.bungee.api.ChatColor.RED + "red");
                    for (PotionEffect effect : player.getActivePotionEffects())
                        player.removePotionEffect(effect.getType());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                    if (GameStart.initActive){
                        player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                    }
                    if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive) {
                        new GameStart().startCountdown();
                    }
                } else {
                    Main.getTeams().getBlueTeam().addEntry(player.getName());
                    player.sendMessage(net.md_5.bungee.api.ChatColor.GRAY + "You joined team " + net.md_5.bungee.api.ChatColor.DARK_AQUA + "blue");
                    for (PotionEffect effect : player.getActivePotionEffects())
                        player.removePotionEffect(effect.getType());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                    if (GameStart.initActive){
                        player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                    }
                    if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                        new GameStart().startCountdown();
                    }
                }
            } else {
                //join random
                int rteam = NumberUtils.random(2,1);
                if (rteam == 1){
                    Main.getTeams().getRedTeam().addEntry(player.getName());
                    player.sendMessage(net.md_5.bungee.api.ChatColor.GRAY + "You joined team " + net.md_5.bungee.api.ChatColor.RED + "red");
                    for (PotionEffect effect : player.getActivePotionEffects())
                        player.removePotionEffect(effect.getType());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                    if (GameStart.initActive){
                        player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                    }
                    if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive) {
                        new GameStart().startCountdown();
                    }
                } else {
                    Main.getTeams().getBlueTeam().addEntry(player.getName());
                    player.sendMessage(net.md_5.bungee.api.ChatColor.GRAY + "You joined team " + net.md_5.bungee.api.ChatColor.DARK_AQUA + "blue");
                    for (PotionEffect effect : player.getActivePotionEffects())
                        player.removePotionEffect(effect.getType());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                    if (GameStart.initActive){
                        player.sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                    }
                    if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                        new GameStart().startCountdown();
                    }
                }
            }
        }


        return true;
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
