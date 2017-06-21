package net.warvale.core.spec;

import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;
import net.warvale.core.commands.admin.BuildCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Preferences implements Listener {


    public Preferences(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private ArrayList<Inventory> invs = new ArrayList<>();

    public static ArrayList<String> noJoinMessages = new ArrayList<>();
    public static ArrayList<String> noLeaveMessages = new ArrayList<>();
    public static ArrayList<String> noTipMessages = new ArrayList<>();
    public static ArrayList<String> noAdvertisementMessages = new ArrayList<>();
    public static ArrayList<String> noPrivateMessages = new ArrayList<>();
    public static ArrayList<String> noChatPings = new ArrayList<>();


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            ItemStack preferences = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
            {
                ItemMeta spawnmeta = preferences.getItemMeta();
                spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Menu & Preferences");
                preferences.setItemMeta(spawnmeta);

                event.getPlayer().getInventory().setItem(6, preferences);
            }
        }
    }

    public void tsGUI(Player player) {

        Inventory invPreferences = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Preferences Menu:");

        // Actual preferences
        List<String> loreJoin = new ArrayList<>();
        loreJoin.clear();
        ItemStack joinpref = new ItemStack(Material.JUNGLE_DOOR_ITEM);
        ItemMeta joinprefmeta = joinpref.getItemMeta();
        joinprefmeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "User Join Messages");
        if (noJoinMessages.contains(player.getName())){
            loreJoin.add(ChatColor.RED.toString() + ChatColor.UNDERLINE + "DISABLED");
        } else {
            loreJoin.add(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "ENABLED");
        }
        joinprefmeta.setLore(loreJoin);
        joinpref.setItemMeta(joinprefmeta);

        List<String> loreLeave = new ArrayList<>();
        ItemStack leavepref = new ItemStack(Material.IRON_DOOR);
        ItemMeta leaveprefmeta = leavepref.getItemMeta();
        leaveprefmeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "User Leave Messages");
        if (noLeaveMessages.contains(player.getName())){
            loreLeave.add(ChatColor.RED.toString() + ChatColor.UNDERLINE + "DISABLED");
        } else {
            loreLeave.add(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "ENABLED");
        }
        leaveprefmeta.setLore(loreLeave);
        leavepref.setItemMeta(leaveprefmeta);

        List<String> loreTip = new ArrayList<>();
        ItemStack tipspref = new ItemStack(Material.BEACON);
        ItemMeta tipsprefmeta = tipspref.getItemMeta();
        tipsprefmeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Tip Messages");
        if (noTipMessages.contains(player.getName())){
            loreTip.add(ChatColor.RED.toString() + ChatColor.UNDERLINE + "DISABLED");
        } else {
            loreTip.add(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "ENABLED");
        }
        tipsprefmeta.setLore(loreTip);
        tipspref.setItemMeta(tipsprefmeta);

        List<String> loreAdvertisement = new ArrayList<>();
        ItemStack advertisementspref = new ItemStack(Material.EMERALD);
        ItemMeta advertisementsprefmeta = advertisementspref.getItemMeta();
        advertisementsprefmeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Advertisement Messages");
        if (noAdvertisementMessages.contains(player.getName())){
            loreAdvertisement.add(ChatColor.RED.toString() + ChatColor.UNDERLINE + "DISABLED");
        } else {
            loreAdvertisement.add(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "ENABLED");
        }
        advertisementsprefmeta.setLore(loreAdvertisement);
        advertisementspref.setItemMeta(advertisementsprefmeta);

        List<String> lorePrivate = new ArrayList<>();
        ItemStack privatemessagespref = new ItemStack(Material.MINECART);
        ItemMeta privatemessagesprefmeta = privatemessagespref.getItemMeta();
        privatemessagesprefmeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Private Messages");
        if (noPrivateMessages.contains(player.getName())){
            lorePrivate.add(ChatColor.RED.toString() + ChatColor.UNDERLINE + "DISABLED");
        } else {
            lorePrivate.add(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "ENABLED");
        }
        privatemessagesprefmeta.setLore(lorePrivate);
        privatemessagespref.setItemMeta(privatemessagesprefmeta);

        List<String> lorePing = new ArrayList<>();
        ItemStack pingmessagespref = new ItemStack(Material.GLOWSTONE);
        ItemMeta pingmessagesprefmeta = pingmessagespref.getItemMeta();
        pingmessagesprefmeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Name Pings");
        if (noChatPings.contains(player.getName())){
            lorePing.add(ChatColor.RED.toString() + ChatColor.UNDERLINE + "DISABLED");
        } else {
            lorePing.add(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "ENABLED");
        }
        pingmessagesprefmeta.setLore(lorePing);
        pingmessagespref.setItemMeta(pingmessagesprefmeta);

        ItemStack prefTrue = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
        ItemMeta prefTruemeta = prefTrue.getItemMeta();
        prefTruemeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "Click to Enable");
        prefTrue.setItemMeta(prefTruemeta);

        ItemStack prefFalse = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta prefFalsemeta = prefFalse.getItemMeta();
        prefFalsemeta.setDisplayName(ChatColor.RED.toString() + ChatColor.UNDERLINE + "Click to Disable");
        prefFalse.setItemMeta(prefFalsemeta);

        // Links

        ItemStack forumslink = new ItemStack(Material.NAME_TAG);
        ItemMeta forumslinkmeta = forumslink.getItemMeta();
        forumslinkmeta.setDisplayName(ChatColor.AQUA + "Forums Link");
        forumslink.setItemMeta(forumslinkmeta);

        ItemStack discordlink = new ItemStack(Material.ANVIL);
        ItemMeta discordlinkmeta = discordlink.getItemMeta();
        discordlinkmeta.setDisplayName(ChatColor.AQUA + "Discord Link");
        discordlink.setItemMeta(discordlinkmeta);

        ItemStack twitterlink = new ItemStack(Material.DIAMOND);
        ItemMeta twitterlinkmeta = twitterlink.getItemMeta();
        twitterlinkmeta.setDisplayName(ChatColor.AQUA + "Twitter Link");
        twitterlink.setItemMeta(twitterlinkmeta);

        ItemStack storelink = new ItemStack(Material.NETHER_STAR);
        ItemMeta storelinkmeta = storelink.getItemMeta();
        storelinkmeta.setDisplayName(ChatColor.AQUA + "Store Link");
        storelink.setItemMeta(storelinkmeta);

        ItemStack closemenu = new ItemStack(Material.BARRIER);
        ItemMeta closemenumeta = closemenu.getItemMeta();
        closemenumeta.setDisplayName(ChatColor.AQUA + "Close selector");
        closemenu.setItemMeta(closemenumeta);

        ItemStack nextpage = new ItemStack(Material.ARROW);
        ItemMeta nextpagemeta = nextpage.getItemMeta();
        nextpagemeta.setDisplayName(ChatColor.AQUA + "Next Page");
        nextpage.setItemMeta(nextpagemeta);

        invPreferences.setItem(0, joinpref);
        invPreferences.setItem(2, leavepref);
        invPreferences.setItem(4, tipspref);
        invPreferences.setItem(6, advertisementspref);
        invPreferences.setItem(8, privatemessagespref);
        invPreferences.setItem(27, pingmessagespref);

        if (!noJoinMessages.contains(player.getName())) {
            invPreferences.setItem(9, prefFalse);
        } else {
            invPreferences.setItem(9, prefTrue);
        }
        if (!noLeaveMessages.contains(player.getName())){
            invPreferences.setItem(11, prefFalse);
        } else {
            invPreferences.setItem(11, prefTrue);
        }
        if (!noTipMessages.contains(player.getName())){
            invPreferences.setItem(13, prefFalse);
        } else {
            invPreferences.setItem(13, prefTrue);
        }
        if (!noAdvertisementMessages.contains(player.getName())){
            invPreferences.setItem(15, prefFalse);
        } else {
            invPreferences.setItem(15, prefTrue);
        }
        if (!noPrivateMessages.contains(player.getName())){
            invPreferences.setItem(17, prefFalse);
        } else {
            invPreferences.setItem(17, prefTrue);
        }
        if (!noChatPings.contains(player.getName())){
            invPreferences.setItem(36, prefFalse);
        } else {
            invPreferences.setItem(36, prefTrue);
        }

        invPreferences.setItem(47, forumslink);
        invPreferences.setItem(48, discordlink);
        invPreferences.setItem(49, twitterlink);
        invPreferences.setItem(50, storelink);
        invPreferences.setItem(51, closemenu);
        invPreferences.setItem(53, nextpage);
        invs.add(invPreferences);
        player.openInventory(invPreferences);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action a = event.getAction();
        ItemStack is = event.getItem();

        if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
            return;



        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName()) && is.getType() == Material.REDSTONE_COMPARATOR) {
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
            new Preferences(Main.get()).tsGUI(event.getPlayer());
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void guiClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!invs.contains(event.getInventory())) {
            return;
        }

        switch (event.getSlot()) {

        // Join Messages
        case 0:
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "User Join Messages"
                    + ChatColor.RESET + ChatColor.GRAY + ": Toggle whether you see users join the server.");
            if (!noJoinMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.GREEN + "ENABLED");
            } else {
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.RED + "DISABLED");
            }
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
            player.closeInventory();
            break;

        case 9:
            event.setCancelled(true);
            if (!noJoinMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "User Join Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.RED + "disabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_CLOSE, 1, 2);
                noJoinMessages.add(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            } else {
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "User Join Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.GREEN + "enabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                noJoinMessages.remove(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            }



        // Leave Messages
        case 2:
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "User Leave Messages"
                    + ChatColor.RESET + ChatColor.GRAY + ": Toggle whether you see users leave the server.");
            if (!noLeaveMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.GREEN + "ENABLED");
            } else {
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.RED + "DISABLED");
            }
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
            player.closeInventory();
            break;

        case 11:
            event.setCancelled(true);
            if (!noLeaveMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "User Leave Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.RED + "disabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_CLOSE, 1, 2);
                noLeaveMessages.add(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            } else {
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "User Leave Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.GREEN + "enabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                noLeaveMessages.remove(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            }

        // Tips
        case 4:
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Tip Messages"
                    + ChatColor.RESET + ChatColor.GRAY + ": Toggle whether you see tips in chat.");
            if (!noTipMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.GREEN + "ENABLED");
            } else {
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.RED + "DISABLED");
            }
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
            player.closeInventory();
            break;

        case 13:
            if (!noTipMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Tip Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.RED + "disabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_CLOSE, 1, 2);
                noTipMessages.add(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            } else {
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Tip Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.GREEN + "enabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                noTipMessages.remove(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            }


        // Advertisements
        case 6:
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Advertisement Messages"
                    + ChatColor.RESET + ChatColor.GRAY + ": Toggle whether you see advertisements in chat.");
            if (!noAdvertisementMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.GREEN + "ENABLED");
            } else {
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.RED + "DISABLED");
            }
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
            player.closeInventory();
            break;

        case 15:
            if (!noAdvertisementMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Advertisement Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.RED + "disabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_CLOSE, 1, 2);
                noAdvertisementMessages.add(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            } else {
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Advertisement Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.GREEN + "enabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                noAdvertisementMessages.remove(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            }


        // Private Messages
        case 8:
            event.setCancelled(true);
            event.getWhoClicked()
                    .sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Private Messages" + ChatColor.RESET
                            + ChatColor.GRAY + ": Toggle whether you see private messages from regular users in chat.");
            if (!noPrivateMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.GREEN + "ENABLED");
            } else {
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.RED + "DISABLED");
            }
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
            player.closeInventory();
            break;

        case 17:
            if (!noPrivateMessages.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Private Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.RED + "disabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_CLOSE, 1, 2);
                noPrivateMessages.add(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            } else {
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Private Messages"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.GREEN + "enabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                noPrivateMessages.remove(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            }

        // Chat Pings
        case 27:
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Pings"
                    + ChatColor.RESET + ChatColor.GRAY + ": Toggle whether you hear a ding if your name is said in chat.");
            if (!noChatPings.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.GREEN + "ENABLED");
            } else {
                event.getWhoClicked().sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Current Status: " + ChatColor.RED + "DISABLED");
            }
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 2);
            player.closeInventory();
            break;

        case 36:
            event.setCancelled(true);
            if (!noChatPings.contains(event.getWhoClicked().getName())){
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Pings"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.RED + "disabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_FENCE_GATE_CLOSE, 1, 2);
                noChatPings.add(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            } else {
                event.getWhoClicked().sendMessage(org.bukkit.ChatColor.DARK_GRAY + "[" + org.bukkit.ChatColor.DARK_RED + "Warvale" + org.bukkit.ChatColor.DARK_GRAY + "] " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Chat Pings"
                        + ChatColor.RESET + ChatColor.GRAY + " have been set to " + ChatColor.GREEN + "enabled!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
                noChatPings.remove(event.getWhoClicked().getName());
                player.closeInventory();
                break;
            }


        // Links
        case 47:
            event.setCancelled(true);
            event.getWhoClicked()
                    .sendMessage(ChatColor.GRAY + "Warvale Forums: " + ChatColor.AQUA + "Forums coming soon.");
            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
            player.closeInventory();
            break;
        case 48:
            event.setCancelled(true);
            event.getWhoClicked()
                    .sendMessage(ChatColor.GRAY + "Warvale Discord: " + ChatColor.AQUA + "https://discord.io/warv");
            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
            player.closeInventory();
            break;
        case 49:
            event.setCancelled(true);
            event.getWhoClicked()
                    .sendMessage(ChatColor.GRAY + "Warvale Twitter: " + ChatColor.AQUA + "www.twitter.com/warvalenetwork");
            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
            player.closeInventory();
            break;
        case 50:
            event.setCancelled(true);
            event.getWhoClicked()
                    .sendMessage(ChatColor.GRAY + "Warvale Store: " + ChatColor.AQUA + "http://store.warvale.net");
            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
            player.closeInventory();
            break;
        case 51:
            event.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_CLOSE, 1, 1);
            player.closeInventory();
            break;
        case 53:
            event.setCancelled(true);
            event.getWhoClicked().sendMessage(ChatColor.GRAY + "There is currently only 1 page!");
            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
            player.closeInventory();
            break;
        default:
            if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getWhoClicked().getName())) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            if (event.getItemDrop().getItemStack().getType() == Material.REDSTONE_COMPARATOR
                    || event.getItemDrop().getItemStack().getType() == Material.NETHER_STAR
                    || event.getItemDrop().getItemStack().getType() == Material.BLAZE_POWDER) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerOffHand(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlaceAttempt(BlockPlaceEvent event) {
        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName()) && !(BuildCommand.canBuild.contains(event.getPlayer()))) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }

    }


    }

