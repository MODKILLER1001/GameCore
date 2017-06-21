package net.warvale.core.spec;

import java.util.*;

import net.warvale.core.classes.Class;
import net.warvale.core.classes.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;

public class ClassSelect implements Listener {

    private static HashMap<Integer, Class> slots = new HashMap<>();
    private static Inventory inv;
    private ArrayList<Inventory> invs = new ArrayList<Inventory>();

    public ClassSelect(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            ItemStack classselect = new ItemStack(Material.NETHER_STAR, 1);
            {
                ItemMeta spawnmeta = classselect.getItemMeta();
                spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Class Selector");
                classselect.setItemMeta(spawnmeta);
                event.getPlayer().getInventory().setItem(2, classselect);
            }
        }
    }

    public void openGUI(Player player) {
        inv = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Select a class: ");
        Integer inventoryIndex = 0;
        for (Map.Entry<String, Class> clazzSet : ClassManager.classes.entrySet()) {
            Class clazz = clazzSet.getValue();
            ItemStack classStack = clazz.getItem();
            ItemMeta classMeta = classStack.getItemMeta();
            List<String> desc = new LinkedList<>(clazz.getDescription());
            desc.add(0, ChatColor.translateAlternateColorCodes('&', "&7PRICE: &e" + clazz.getPrice()));
            desc.add(1, ChatColor.GRAY + "ABILITY: " + ChatColor.YELLOW + clazz.getAbility());
            classMeta.setLore(desc);
            classMeta.setDisplayName(ChatColor.AQUA + clazz.getName());
            classStack.setItemMeta(classMeta);
            inv.setItem(inventoryIndex, classStack);
            slots.put(inventoryIndex, clazz);
            inventoryIndex = inventoryIndex + 1;
        }
        invs.add(inv);
        player.openInventory(inv);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action a = event.getAction();
        ItemStack is = event.getItem();

        if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR) return;

        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName()) && is.getType() == Material.NETHER_STAR) {
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
            new ClassSelect(Main.get()).openGUI(event.getPlayer());
            event.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Class clazz = slots.get(event.getSlot());

        if (!invs.contains(inv)) {
            return;
        }

        if (clazz == null) {
            return;
        }
        // if (!ClassManager.hasClass(player)) {
        clazz.addMember(player);
        player.sendMessage(ChatColor.GRAY + "You have successfully chosen the " + ChatColor.YELLOW + clazz.getName()
                + ChatColor.GRAY + " class!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
        //new ClassChooseEvent(player, clazz);
        // } else {
        // player.sendMessage(ChatColor.RED + "You may not switch your class at
        // this time.");
        // }
        event.setCancelled(true);
        player.closeInventory();
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {

        if (event.getItemDrop().getItemStack().getType() == Material.ENCHANTED_BOOK
                || event.getItemDrop().getItemStack().getType() == Material.NETHER_STAR
                || event.getItemDrop().getItemStack().getType() == Material.BLAZE_POWDER) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerOffHand(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
    }

}
