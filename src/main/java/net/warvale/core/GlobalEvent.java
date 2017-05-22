package net.warvale.core;

import net.warvale.core.utils.NumberUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Draem on 5/8/2017.
 */
public class GlobalEvent implements Listener {

    GlobalEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.IRON_ORE)) {
            event.getPlayer().giveExp(10);
            for (int i = 0; i < NumberUtils.random(3, 1); i++) {
                event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT));
            }
            event.setCancelled(true);
            Location blockLoc = event.getBlock().getLocation();
            event.getBlock().setType(Material.STONE);
            List<Entity> entityList = new ArrayList<>();
            for (int i = 0; i < NumberUtils.random(5, 2) + 1; i++) {
                ItemStack stack = new ItemStack(Material.IRON_NUGGET);
                stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, NumberUtils.random(100, 1));
                entityList.add(event.getPlayer().getWorld().dropItemNaturally(
                        event.getBlock().getLocation().setDirection(Vector.getRandom()).add(Vector.getRandom()),
                        stack));
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    entityList.forEach(Entity::remove);
                }
            }.runTaskLater(Main.get(), 60L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().getWorld().getBlockAt(blockLoc).setType(Material.IRON_ORE);
                    event.getPlayer().getWorld().playSound(blockLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 1);
                }
            }.runTaskLater(Main.get(), 400L);
        }
        if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
            event.getPlayer().giveExp(30);
            for (int i = 0; i < NumberUtils.random(2, 0); i++) {
                event.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
            }
            event.setCancelled(true);
            Location blockLoc = event.getBlock().getLocation();
            event.getBlock().setType(Material.STONE);
            List<Entity> entityList = new ArrayList<>();
            for (int i = 0; i < NumberUtils.random(5, 2) + 1; i++) {
                ItemStack stack = new ItemStack(Material.GOLD_NUGGET);
                stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, NumberUtils.random(100, 1));
                entityList.add(event.getPlayer().getWorld().dropItemNaturally(
                        event.getBlock().getLocation().setDirection(Vector.getRandom()).add(Vector.getRandom()),
                        stack));
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    entityList.stream().forEach(entity -> entity.remove());
                }
            }.runTaskLater(Main.get(), 60L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().getWorld().getBlockAt(blockLoc).setType(Material.GOLD_ORE);
                    event.getPlayer().getWorld().playSound(blockLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                }
            }.runTaskLater(Main.get(), 400L);
        }
        if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
            event.getPlayer().giveExp(50);
            for (int i = 0; i < NumberUtils.random(1, 0); i++) {
                event.getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND));
            }
            event.setCancelled(true);
            Location blockLoc = event.getBlock().getLocation();
            event.getBlock().setType(Material.STONE);
            List<Entity> entityList = new ArrayList<>();
            for (int i = 0; i < NumberUtils.random(5, 2) + 1; i++) {
                ItemStack stack = new ItemStack(Material.PRISMARINE_CRYSTALS);
                stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, NumberUtils.random(100, 1));
                entityList.add(event.getPlayer().getWorld().dropItemNaturally(
                        event.getBlock().getLocation().setDirection(Vector.getRandom()).add(Vector.getRandom()),
                        stack));
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    entityList.stream().forEach(entity -> entity.remove());
                }
            }.runTaskLater(Main.get(), 60L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().getWorld().getBlockAt(blockLoc).setType(Material.DIAMOND_ORE);
                    event.getPlayer().getWorld().playSound(blockLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 25, 1);
                }
            }.runTaskLater(Main.get(), 400L);
        }    
        if (event.getBlock().getType().equals(Material.LAPIS_ORE)) {
            event.getPlayer().giveExp(50);
            for (int i = 0; i < NumberUtils.random(1, 0); i++) {
                event.getPlayer().getInventory().addItem(new ItemStack(Material.LAPIS_BLOCK));
            }
            event.setCancelled(true);
            Location blockLoc = event.getBlock().getLocation();
            event.getBlock().setType(Material.STONE);
            List<Entity> entityList = new ArrayList<>();
            for (int i = 0; i < NumberUtils.random(5, 2) + 1; i++) {
                ItemStack stack = new ItemStack(Material.EYE_OF_ENDER);
                stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, NumberUtils.random(100, 1));
                entityList.add(event.getPlayer().getWorld().dropItemNaturally(
                        event.getBlock().getLocation().setDirection(Vector.getRandom()).add(Vector.getRandom()),
                        stack));
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    entityList.stream().forEach(entity -> entity.remove());
                }
            }.runTaskLater(Main.get(), 60L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().getWorld().getBlockAt(blockLoc).setType(Material.LAPIS_ORE);
                    event.getPlayer().getWorld().playSound(blockLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 1);
                }
            }.runTaskLater(Main.get(), 400L);
        }   
        
        
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.IRON_NUGGET)) {
            event.setCancelled(true);
        }
        if (event.getItem().getItemStack().getType().equals(Material.GOLD_NUGGET)) {
            event.setCancelled(true);
        }
        if (event.getItem().getItemStack().getType().equals(Material.PRISMARINE_CRYSTALS)) {
            event.setCancelled(true);
        }
        if (event.getItem().getItemStack().getType().equals(Material.EYE_OF_ENDER)) {
            event.setCancelled(true);    
        }
    }


}
