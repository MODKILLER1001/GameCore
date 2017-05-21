package net.warvale.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.warvale.core.Main;

public class StartAuto implements CommandExecutor {
    private Main plugin;
    int task;
    Integer startCountdown = 10;

    public StartAuto(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {

        if (cs instanceof Player) {
            Player sender = (Player) cs;
            if (command.getName().equalsIgnoreCase("start")) {
                sender.sendMessage(ChatColor.GRAY + "Starting the game automatically..");
                task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(ChatColor.DARK_RED + "CTF " + ChatColor.GRAY + "starts in "
                                + startCountdown + " seconds.");
                        startCountdown--;

                        if (startCountdown <= 0) {
                            startCountdown = 10;
                            Bukkit.getScheduler().cancelTask(task);
                            Bukkit.broadcastMessage(ChatColor.GRAY + "The game has begun on <map>!");
                        }
                    }
                }, 0, 20);
            }
        }
        return true;
    }
}