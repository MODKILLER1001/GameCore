package net.warvale.core.game.logic;

import net.warvale.core.map.ConquestMap;
import net.warvale.core.maps.GameMap;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by Draem on 6/19/2017.
 */
public class RegionProtection implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        for (GameMap map : GameMap.getMaps()) {
            if (new ConquestMap(map.getName()).crossCheckCoords(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())) {
                event.getPlayer().sendMessage(ChatColor.RED + "You may not edit the blocks here.");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        for (GameMap map : GameMap.getMaps()) {
            if (new ConquestMap(map.getName()).crossCheckCoords(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ())) {
                event.getPlayer().sendMessage(ChatColor.RED + "You may not edit the blocks here.");
                event.setCancelled(true);
            }
        }
    }

}
