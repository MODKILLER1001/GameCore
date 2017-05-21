package net.warvale.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Start {
    private Main plugin;

    public Start(Main plugin) {
        this.plugin = plugin;
    }

    public boolean initiateGame() {
        if (Main.getRedTeam().getEntries().toArray().length >= 1
                && Main.getBlueTeam().getEntries().toArray().length >= 1) {
            BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                int time = 10;

                @Override
                public void run() {
                    if (!(time <= 0)) {
                        time--;
                        Bukkit.broadcastMessage(
                                ChatColor.DARK_RED + "CTF" + ChatColor.GRAY + " starts in " + time + " seconds.");
                    } else {
                        time = 10;
                        Bukkit.broadcastMessage(ChatColor.DARK_RED + "CTF" + ChatColor.GRAY + " is now starting.");
                        cancel();
                    }
                }
            };
            bukkitRunnable.runTask(plugin);
            return true;
        }

        return false;

    }
}
