package net.warvale.core;

import net.warvale.core.chat.ChatNameColorGUI;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.logic.TeamManager;
import net.warvale.core.spec.Preferences;
import net.warvale.core.utils.NumberUtils;
import net.warvale.core.utils.chat.ChatUtils;
import net.warvale.staffcore.listeners.ChatListener;
import net.warvale.staffcore.users.UserManager;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
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

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

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

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

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

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

        event.setDeathMessage("");
        Player vName = event.getEntity();
        Player kName = vName.getKiller();
        ChatColor gray = ChatColor.GRAY;
        String victim = (Main.getTeams().getBlueTeam().hasPlayer(vName) ? ChatColor.DARK_AQUA + vName.getName() : (Main.getTeams().getRedTeam().hasPlayer(vName) ? ChatColor.RED + vName.getName() : ChatColor.AQUA + vName.getName()));
        String killer = (Main.getTeams().getBlueTeam().hasPlayer(kName) ? ChatColor.DARK_AQUA + kName.getName() : (Main.getTeams().getRedTeam().hasPlayer(kName) ? ChatColor.RED + kName.getName() : ChatColor.AQUA + kName.getName()));
        ArrayList<String> deathMessages = new ArrayList<>();
        deathMessages.addAll(Arrays.asList(killer + gray + " squished " + victim + gray + " like a bug.", killer + gray + " delivered the final coup de grâce to " + victim, killer + gray + " assassinated " + victim, killer + gray + " put " + victim + gray + " out of their misery.", killer + gray + " showed no mercy to " + victim, killer + gray + " back-stabbed " + victim, killer + gray + " slayed " + victim, killer + gray + " brutally beat " + victim, killer + gray + " rearranged " + victim + gray + "'s face.", killer + gray + " ended " + victim + gray + "'s pitiful existence.", killer + gray + " removed the limbs of " + victim, killer + gray + " answered " + victim + "'s plea for death.", gray + "Thanks to " + killer + gray + ", " + victim + gray + " no longer exists.", killer + gray + " proved that " + victim + gray + " was no match for them.", killer + gray + " sent " + victim + gray + " to their grave."));
        int r = NumberUtils.random(deathMessages.size() - 1, 0);
        vName.sendMessage(deathMessages.get(r));
        kName.sendMessage(deathMessages.get(r));
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player sender = event.getPlayer();
        String message = event.getMessage();
        event.setCancelled(true);
        if (new ChatListener().isMuted() && !sender.isOp()){
            sender.sendMessage(ChatColor.RED + "Chat is currently disabled.");
            return;
        }
        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            if (message.toLowerCase().contains(player.getName().toLowerCase()) && !(Preferences.noChatPings.contains(player.getName())) && !(player == sender)){
                if (ChatNameColorGUI.playerChatColor.containsKey(player)){
                    String newMessage = message.replaceAll(player.getName(), ChatNameColorGUI.playerChatColor.get(player) + player.getName() + ChatColor.WHITE);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', UserManager.getUser(sender.getUniqueId()).getPrefix() + UserManager.getUser(sender.getUniqueId()).getNameColor() + sender.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + newMessage));
                } else {
                    String newMessage = message.replaceAll(player.getName(), ChatColor.YELLOW + player.getName() + ChatColor.WHITE);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', UserManager.getUser(sender.getUniqueId()).getPrefix() + UserManager.getUser(sender.getUniqueId()).getNameColor() + sender.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + newMessage));
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', UserManager.getUser(sender.getUniqueId()).getPrefix() + UserManager.getUser(sender.getUniqueId()).getNameColor() + sender.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message));
            }

        }
    }


}
