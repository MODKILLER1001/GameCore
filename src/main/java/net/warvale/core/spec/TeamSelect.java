package net.warvale.core.spec;

import net.warvale.core.game.start.GameStart;
import net.warvale.core.utils.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
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
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;

import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;

public class TeamSelect implements Listener {

    public TeamSelect(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static Inventory invTeamSelect;

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            ItemStack teamselect = new ItemStack(Material.ENCHANTED_BOOK, 1);
            {
                ItemMeta spawnmeta = teamselect.getItemMeta();
                spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Team Selector");
                teamselect.setItemMeta(spawnmeta);

                event.getPlayer().getInventory().setItem(4, teamselect);
            }
        }
    }

    public void tsGUI(Player player) {
        invTeamSelect = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Select a team:");

        Wool wool_red = new Wool(DyeColor.RED);
        ItemStack itemtsred = wool_red.toItemStack(1);

        ItemMeta itemtsredmeta = itemtsred.getItemMeta();
        itemtsredmeta.setDisplayName(ChatColor.RED + "Red");
        itemtsred.setItemMeta(itemtsredmeta);

        Wool wool_cyan = new Wool(DyeColor.CYAN);
        ItemStack itemtscyan = wool_cyan.toItemStack(1);

        ItemMeta itemtscyanmeta = itemtscyan.getItemMeta();
        itemtscyanmeta.setDisplayName(ChatColor.DARK_AQUA + "Blue");
        itemtscyan.setItemMeta(itemtscyanmeta);

        Wool wool_auto = new Wool(DyeColor.PURPLE);
        ItemStack itemtsauto = wool_auto.toItemStack(1);

        ItemMeta itemtsautometa = itemtsauto.getItemMeta();
        itemtsautometa.setDisplayName(ChatColor.GOLD + "Auto Join");
        itemtsauto.setItemMeta(itemtsautometa);

        ItemStack closemenu = new ItemStack(Material.BARRIER, 1);
        ItemMeta closemenumeta = closemenu.getItemMeta();
        closemenumeta.setDisplayName(ChatColor.DARK_RED + "Close selector");
        closemenu.setItemMeta(closemenumeta);

        invTeamSelect.setItem(2, itemtsred);
        invTeamSelect.setItem(4, itemtsauto);
        invTeamSelect.setItem(8, closemenu);
        invTeamSelect.setItem(6, itemtscyan);

        player.openInventory(invTeamSelect);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action a = event.getAction();
        ItemStack is = event.getItem();

        if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR)
            return;

        if (is.getType() == Material.ENCHANTED_BOOK)
            tsGUI(event.getPlayer());

        if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!event.getInventory().equals(invTeamSelect)) {
            if (Main.getTeams().getSpectatorTeam().getEntries().contains(event.getWhoClicked().getName())) {
                event.setCancelled(true);
                return;
            }
        }
        switch (event.getSlot()) {
        case 2: // Join red
            if (event.getInventory().equals(invTeamSelect)) {
                if (!event.getWhoClicked().hasPermission("warvale.teamSelect")){
                    event.getWhoClicked().sendMessage(ChatColor.RED + "You must have at least a " + ChatColor.DARK_PURPLE + "mythic" + " rank to select a team. Use Auto Join instead.");
                    event.setCancelled(true);
                    player.closeInventory();
                    break;
                }
                if (Main.getTeams().getRedTeam().getSize() - Main.getTeams().getBlueTeam().getSize() >= 2){
                    event.getWhoClicked().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "This team is full!");
                    event.setCancelled(true);
                    player.closeInventory();
                    break;
                }
                Main.getTeams().getRedTeam().addEntry(event.getWhoClicked().getName());
                event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
                for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                    event.getWhoClicked().removePotionEffect(effect.getType());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                if (GameStart.initActive){
                    event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                }
                if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive) {
                    new GameStart().startCountdown();
                }
                event.setCancelled(true);
                player.closeInventory();
            }
            break;
        case 8: // Close menu
            if (event.getInventory().equals(invTeamSelect)) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_CLOSE, 1, 1);
                player.closeInventory();
            }
            break;
        case 6: // Join blue
            if (event.getInventory().equals(invTeamSelect)) {
                if (!event.getWhoClicked().hasPermission("warvale.teamSelect")){
                    event.getWhoClicked().sendMessage(ChatColor.RED + "You must have at least a " + ChatColor.DARK_PURPLE + "mythic" + " rank to select a team. Use Auto Join instead.");
                    event.setCancelled(true);
                    player.closeInventory();
                    break;
                }
                if (Main.getTeams().getBlueTeam().getSize() - Main.getTeams().getRedTeam().getSize() >= 2){
                    event.getWhoClicked().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "This team is full!");
                    event.setCancelled(true);
                    player.closeInventory();
                    break;
                }
                Main.getTeams().getBlueTeam().addEntry(event.getWhoClicked().getName());
                event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
                for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                    event.getWhoClicked().removePotionEffect(effect.getType());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                if (GameStart.initActive){
                    event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                }
                if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                    new GameStart().startCountdown();
                }
                event.setCancelled(true);
                player.closeInventory();
            }
            break;
        case 4: //auto join
            if (event.getInventory().equals(invTeamSelect)){
                if (Main.getTeams().getBlueTeam().getEntries().size() > Main.getTeams().getRedTeam().getEntries().size()){
                    //join blue
                    Main.getTeams().getBlueTeam().addEntry(event.getWhoClicked().getName());
                    event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
                    for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                        event.getWhoClicked().removePotionEffect(effect.getType());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                    if (GameStart.initActive){
                        event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                    }
                    if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                        new GameStart().startCountdown();
                    }
                    event.setCancelled(true);
                    player.closeInventory();
                    break;
                } else if (Main.getTeams().getRedTeam().getEntries().size() > Main.getTeams().getBlueTeam().getEntries().size()){
                    //join red
                    Main.getTeams().getRedTeam().addEntry(event.getWhoClicked().getName());
                    event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
                    for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                        event.getWhoClicked().removePotionEffect(effect.getType());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                    if (GameStart.initActive){
                        event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                    }
                    if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive) {
                        new GameStart().startCountdown();
                    }
                    event.setCancelled(true);
                    player.closeInventory();
                    break;
                } else if (Main.getTeams().getBlueTeam().getEntries().size() == Main.getTeams().getRedTeam().getEntries().size()){
                    int team = NumberUtils.random(2,1);
                    if (team == 1){
                        Main.getTeams().getRedTeam().addEntry(event.getWhoClicked().getName());
                        event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
                        for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                            event.getWhoClicked().removePotionEffect(effect.getType());
                        player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                        if (GameStart.initActive){
                            event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                        }
                        if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive) {
                            new GameStart().startCountdown();
                        }
                        event.setCancelled(true);
                        player.closeInventory();
                        break;
                    } else {
                        Main.getTeams().getBlueTeam().addEntry(event.getWhoClicked().getName());
                        event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
                        for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                            event.getWhoClicked().removePotionEffect(effect.getType());
                        player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                        if (GameStart.initActive){
                            event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                        }
                        if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                            new GameStart().startCountdown();
                        }
                        event.setCancelled(true);
                        player.closeInventory();
                        break;
                    }
                } else {
                    //join random
                    int team = NumberUtils.random(2,1);
                    if (team == 1){
                        Main.getTeams().getRedTeam().addEntry(event.getWhoClicked().getName());
                        event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
                        for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                            event.getWhoClicked().removePotionEffect(effect.getType());
                        player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                        if (GameStart.initActive){
                            event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                        }
                        if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive) {
                            new GameStart().startCountdown();
                        }
                        event.setCancelled(true);
                        player.closeInventory();
                        break;
                    } else {
                        Main.getTeams().getBlueTeam().addEntry(event.getWhoClicked().getName());
                        event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
                        for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
                            event.getWhoClicked().removePotionEffect(effect.getType());
                        player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
                        if (GameStart.initActive){
                            event.getWhoClicked().sendMessage(org.bukkit.ChatColor.RED.toString() + org.bukkit.ChatColor.BOLD + "/vote" + org.bukkit.ChatColor.BLUE.toString() + org.bukkit.ChatColor.BOLD + " to vote for a map!");
                        }
                        if (Main.getTeams().getRedTeam().getSize() >= 1 && Main.getTeams().getBlueTeam().getSize() >= 1 && !GameStart.initActive){
                            new GameStart().startCountdown();
                        }
                        event.setCancelled(true);
                        player.closeInventory();
                        break;
                    }
                }
            }
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
