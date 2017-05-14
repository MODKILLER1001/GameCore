package warvale.core.plugin.spec;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
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
import warvale.core.plugin.Main;

public class Preferences implements Listener {
	
    public Preferences(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
	    if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
	        ItemStack preferences = new ItemStack(Material.REDSTONE_COMPARATOR, 1); {
	        ItemMeta spawnmeta = preferences.getItemMeta();
	        spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Menu & Preferences");
	        preferences.setItemMeta(spawnmeta);
	        
	        event.getPlayer().getInventory().setItem(6, preferences);
	        }
	    }
	}
	
	private void tsGUI(Player player ) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Preferences Menu:");
		
		ItemStack forumslink = new ItemStack(Material.NAME_TAG);
		ItemMeta forumslinkmeta = forumslink.getItemMeta();
		forumslinkmeta.setDisplayName(ChatColor.DARK_RED + "Forums Link");
		forumslink.setItemMeta(forumslinkmeta);
		
		ItemStack discordlink = new ItemStack(Material.ANVIL);
		ItemMeta discordlinkmeta = discordlink.getItemMeta();
		discordlinkmeta.setDisplayName(ChatColor.DARK_RED + "Discord Link");
		discordlink.setItemMeta(discordlinkmeta);
		
		ItemStack twitterlink = new ItemStack(Material.DIAMOND);
		ItemMeta twitterlinkmeta = twitterlink.getItemMeta();
		twitterlinkmeta.setDisplayName(ChatColor.DARK_RED + "Twitter Link");
		twitterlink.setItemMeta(twitterlinkmeta);
		
		ItemStack storelink = new ItemStack(Material.NETHER_STAR);
		ItemMeta storelinkmeta = storelink.getItemMeta();
		storelinkmeta.setDisplayName(ChatColor.DARK_RED + "Store Link");
		storelink.setItemMeta(storelinkmeta);
		
		ItemStack closemenu = new ItemStack(Material.BARRIER);
		ItemMeta closemenumeta = closemenu.getItemMeta();
		closemenumeta.setDisplayName(ChatColor.DARK_RED + "Close selector");
		closemenu.setItemMeta(closemenumeta);
		
		inv.setItem(49, forumslink);
		inv.setItem(50, discordlink);
		inv.setItem(51, twitterlink);
		inv.setItem(52, storelink);
		inv.setItem(53, closemenu);
		
		player.openInventory(inv);
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
			Action a = event.getAction();
			ItemStack is = event.getItem();
			
			if(a == Action.PHYSICAL || is == null || is.getType()==Material.AIR)
				return;
			
			if(is.getType()==Material.REDSTONE_COMPARATOR)
				tsGUI(event.getPlayer());
		
	}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onMove(InventoryClickEvent event){
		if (Main.getSpectatorTeam().getEntries().contains(event.getWhoClicked().getName())) {
		    Player player = (Player) event.getWhoClicked();
		    event.setCancelled(true);
	    
	   }
	}
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
			if (event.getItemDrop().getItemStack().getType() == Material.REDSTONE_COMPARATOR ||event.getItemDrop().getItemStack().getType() == Material.NETHER_STAR || event.getItemDrop().getItemStack().getType() == Material.BLAZE_POWDER ) {
				event.setCancelled(true);
	}
	}
	}
	@EventHandler
	public void onPlayerOffHand(PlayerSwapHandItemsEvent event) {
		event.setCancelled(true);
	}
		
	}
