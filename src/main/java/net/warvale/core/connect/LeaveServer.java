package net.warvale.core.connect;

import net.warvale.core.game.scoreboards.LobbyScoreboard;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;

public class LeaveServer implements Listener {

    public LeaveServer(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event) {

        String playerName = event.getPlayer().getName();
        event.getPlayer().getInventory().clear();

        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            Main.getTeams().getSpectatorTeam().removeEntry(event.getPlayer().getName());
        }

        if (Main.getTeams().getRedTeam().getEntries().contains(event.getPlayer().getName())) {
            Main.getTeams().getRedTeam().removeEntry(event.getPlayer().getName());
        }

        if (Main.getTeams().getBlueTeam().getEntries().contains(event.getPlayer().getName())) {
            Main.getTeams().getBlueTeam().removeEntry(event.getPlayer().getName());
        }

        event.setQuitMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " left.");

        LobbyScoreboard.getInstance().removeScoreboard(event.getPlayer());

        BarManager.broadcast(BarColor.RED, ChatColor.DARK_RED + ChatColor.BOLD.toString() + "[-] " + ChatColor.RESET + playerName);
        BarManager.broadcastSound(Sound.BLOCK_NOTE_BASS);

    }
}
