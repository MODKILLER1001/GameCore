package warvale.core.plugin.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import warvale.core.plugin.Main;
import warvale.core.plugin.utils.NumberUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Draem on 5/8/2017.
 */
public class WorldEvent implements Listener {

    public WorldEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.IRON_ORE)) {
            event.getPlayer().giveExp(10);
            for (int i = 1; i < NumberUtils.random(3, 1); i++) {
                event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
            }
            event.setCancelled(true);
            event.getBlock().breakNaturally(new ItemStack(Material.AIR));
            event.getBlock().setType(Material.STONE);
            new Thread(() -> {
                try {
                    for (int i = 1; i < NumberUtils.random(5, 2); i++) {
                        event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation().setDirection(Vector.getRandom()), new ItemStack(Material.IRON_NUGGET));
                    }

                    TimeUnit.SECONDS.sleep(10);
                    event.getBlock().setType(Material.IRON_ORE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).run();
        }
    }

}
