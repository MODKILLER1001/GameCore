package net.warvale.core.chat;

import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

/**
 * Created by AAces on 6/10/2017.
 */
//TODO: Move to StaffCore
public class ChatNameColorGUI implements Listener {
    public ChatNameColorGUI(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    public static HashMap<Player, ChatColor> playerChatColor = new HashMap<>();
    private static Inventory inv;
    public static void colorGUI(Player player){
        inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Select Chat Name Color:");

        //colors
        ItemStack orange = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
        ItemMeta orangemeta = orange.getItemMeta();
        orangemeta.setDisplayName(ChatColor.GOLD + "Orange");
        orange.setItemMeta(orangemeta);

        ItemStack yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
        ItemMeta yellowmeta = yellow.getItemMeta();
        yellowmeta.setDisplayName(ChatColor.YELLOW + "Yellow");
        yellow.setItemMeta(yellowmeta);

        ItemStack lime = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        ItemMeta limemeta = lime.getItemMeta();
        limemeta.setDisplayName(ChatColor.GREEN + "Lime Green");
        lime.setItemMeta(limemeta);

        ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
        ItemMeta greenmeta = green.getItemMeta();
        greenmeta.setDisplayName(ChatColor.DARK_GREEN + "Green");
        green.setItemMeta(greenmeta);

        ItemStack cyan = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 9);
        ItemMeta cyanmeta = cyan.getItemMeta();
        cyanmeta.setDisplayName(ChatColor.DARK_AQUA + "Cyan");
        cyan.setItemMeta(cyanmeta);

        ItemStack lightblue = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
        ItemMeta lightbluemeta = lightblue.getItemMeta();
        lightbluemeta.setDisplayName(ChatColor.BLUE + "Light Blue");
        lightblue.setItemMeta(lightbluemeta);

        ItemStack blue = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemMeta bluemeta = blue.getItemMeta();
        bluemeta.setDisplayName(ChatColor.DARK_BLUE + "Blue");
        blue.setItemMeta(bluemeta);

        ItemStack purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
        ItemMeta purplemeta = purple.getItemMeta();
        purplemeta.setDisplayName(ChatColor.DARK_PURPLE + "Purple");
        purple.setItemMeta(purplemeta);

        ItemStack pink = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
        ItemMeta pinkmeta = pink.getItemMeta();
        pinkmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Pink");
        pink.setItemMeta(pinkmeta);

        ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta redmeta = red.getItemMeta();
        redmeta.setDisplayName(ChatColor.RED + "Red");
        red.setItemMeta(redmeta);
        //close button
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.DARK_RED + "Close Menu");
        close.setItemMeta(closemeta);

        //place the items in slots
        inv.setItem(9, orange);
        inv.setItem(11, yellow);
        inv.setItem(13, lime);
        inv.setItem(15, green);
        inv.setItem(17, cyan);
        inv.setItem(27, lightblue);
        inv.setItem(29, blue);
        inv.setItem(31, purple);
        inv.setItem(33, pink);
        inv.setItem(35, red);
        inv.setItem(49, close);

        player.openInventory(inv);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!event.getInventory().equals(inv)) {
            return;
        }

        switch (event.getSlot()){
        case 9:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.GOLD + "orange!");
            playerChatColor.put(player, ChatColor.GOLD);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 11:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.YELLOW + "yellow!");
            playerChatColor.put(player, ChatColor.YELLOW);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 13:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.GREEN + "lime green!");
            playerChatColor.put(player, ChatColor.GREEN);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 15:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.DARK_GREEN + "green!");
            playerChatColor.put(player, ChatColor.DARK_GREEN);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 17:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.DARK_AQUA + "cyan!");
            playerChatColor.put(player, ChatColor.DARK_AQUA);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 27:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.BLUE + "light blue!");
            playerChatColor.put(player, ChatColor.BLUE);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 29:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.DARK_BLUE + "blue!");
            playerChatColor.put(player, ChatColor.DARK_BLUE);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 31:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.DARK_PURPLE + "purple!");
            playerChatColor.put(player, ChatColor.DARK_PURPLE);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 33:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.LIGHT_PURPLE + "pink!");
            playerChatColor.put(player, ChatColor.LIGHT_PURPLE);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 35:
            event.setCancelled(true);
            player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Name Color" + ChatColor.RESET + ChatColor.GRAY + " has been set to " + ChatColor.RED + "red!");
            playerChatColor.put(player, ChatColor.RED);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
            player.closeInventory();
            break;
        case 49:
            event.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_CLOSE, 1, 1);
            player.closeInventory();
            break;
        default:
            if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getWhoClicked().getName())) {
                event.setCancelled(true);
                break;
            }
            break;
        }


    }

}
