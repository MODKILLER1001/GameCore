package warvale.core.plugin.events;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import warvale.core.plugin.Main;
import warvale.core.plugin.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;
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
            for (int i = 0; i < NumberUtils.random(2, 1); i++) {
                event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
            }
            event.setCancelled(true);
            Location blockLoc = event.getBlock().getLocation();
            event.getBlock().setType(Material.STONE);
            List<Entity> entityList = new ArrayList<>();
            for (int i = 0; i < NumberUtils.random(5, 2) + 1; i++) {
                ItemStack stack = new ItemStack(Material.IRON_NUGGET);
                stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, NumberUtils.random(100, 1));
                entityList.add(event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation().setDirection(Vector.getRandom()).add(Vector.getRandom()), stack));
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    entityList.stream().forEach(entity -> entity.remove());
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin(main.getDescription().getName()), 60L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().getWorld().getBlockAt(blockLoc).setType(Material.IRON_ORE);
                    event.getPlayer().getWorld().playSound(blockLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 25, 1);
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin(main.getDescription().getName()), 400L);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.IRON_NUGGET)) {
            event.setCancelled(true);
        }
    }
}
