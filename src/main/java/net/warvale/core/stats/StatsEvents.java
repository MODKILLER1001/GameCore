package net.warvale.core.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatsEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //make sure the player stats exists
        if (!StatsManager.getInstance().doesPlayerExsist(player.getUniqueId())) {
            StatsManager.getInstance().createPlayer(player.getUniqueId());
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        StatsManager.getInstance().removePlayer(player.getUniqueId());
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        StatsManager.getInstance().removePlayer(player.getUniqueId());
    }

}
