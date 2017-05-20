package warvale.core.plugin.spec;

import java.util.*;

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
import warvale.core.plugin.Main;
import warvale.core.plugin.classes.Class;
import warvale.core.plugin.classes.ClassManager;

public class ClassSelect implements Listener {

    private static HashMap<Integer, Class> slots = new HashMap<>();
    private static Inventory inv;

    public ClassSelect(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            ItemStack classselect = new ItemStack(Material.NETHER_STAR, 1);
            {
                ItemMeta spawnmeta = classselect.getItemMeta();
                spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Class Selector");
                classselect.setItemMeta(spawnmeta);
                event.getPlayer().getInventory().setItem(2, classselect);
            }
        }
    }

    private void openGUI(Player player) {
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
        player.openInventory(inv);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action a = event.getAction();
        ItemStack is = event.getItem();

        if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
            return;

        if (is.getType() == Material.NETHER_STAR)
            openGUI(event.getPlayer());

        if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Class clazz = slots.get(event.getSlot());

        if (!event.getInventory().equals(inv)) {
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
        // } else {
        // player.sendMessage(ChatColor.RED + "You may not switch your class at
        // this time.");
        // }
        event.setCancelled(true);
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
