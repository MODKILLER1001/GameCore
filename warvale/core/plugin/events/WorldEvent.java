package warvale.core.plugin.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import warvale.core.plugin.Main;
import warvale.core.plugin.utils.NumberUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Draem on 5/8/2017.
 */
public class WorldEvent implements Listener {

    private Main main;

    public WorldEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        main = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.IRON_ORE)) {
            event.getPlayer().giveExp(10);
            for (int i = 0; i < NumberUtils.random(2, 1) + 1; i++) {
                event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
            }
            event.setCancelled(true);
            event.getBlock().breakNaturally(new ItemStack(Material.AIR));
            event.getBlock().setType(Material.STONE);
            for (int i = 0; i < NumberUtils.random(5, 2) + 1; i++) {
                event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation().setDirection(Vector.getRandom()).add(Vector.getRandom()), new ItemStack(Material.IRON_NUGGET));
            }
            Bukkit.getScheduler().runTaskLaterAsynchronously(main, () -> {
                event.getBlock().setType(Material.IRON_ORE);
            }, 10);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.IRON_NUGGET)) {
            event.setCancelled(true);
        }
    }
}
