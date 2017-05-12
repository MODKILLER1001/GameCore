package warvale.core.plugin.spec;

import java.util.ArrayList;
import java.util.List;

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

public class ClassSelect implements Listener {
	
    public ClassSelect(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
    @EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
	    if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
	        ItemStack classselect = new ItemStack(Material.NETHER_STAR, 1); {
	        ItemMeta spawnmeta = classselect.getItemMeta();
	        spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Class Selector");
	        classselect.setItemMeta(spawnmeta);
	        Main.getClassPicked().addEntry(event.getPlayer().getName());
	        
	        
	        event.getPlayer().getInventory().setItem(2, classselect);
	        }
	    }
	}
	
	private void tsGUI(Player player ) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Select a class:");
	
		// Soldier
		ItemStack soldier_icon = new ItemStack(Material.IRON_HELMET, 1);
		ItemMeta soldier_iconmeta = soldier_icon.getItemMeta();
		soldier_iconmeta.setDisplayName(ChatColor.AQUA + "Soldier");
		ArrayList<String> lore_soldier = new ArrayList<String>();

		lore_soldier.add(ChatColor.YELLOW + "Available by default.");
		lore_soldier.add(ChatColor.GRAY + "Ascend walls on right click of your ability!");
		
        soldier_iconmeta.setLore(lore_soldier);
        soldier_icon.setItemMeta(soldier_iconmeta);
		
        // Hunter
		ItemStack hunter_icon = new ItemStack(Material.BOW, 1);
		ItemMeta hunter_iconmeta = hunter_icon.getItemMeta();
		hunter_iconmeta.setDisplayName(ChatColor.AQUA + "Hunter");
		ArrayList<String> lore_hunter = new ArrayList<String>();
		
		lore_hunter.add(ChatColor.YELLOW + "Available by default.");
		lore_hunter.add(ChatColor.GRAY + "Fire a bomb arrow on right click of your ability!");
		
		
        hunter_iconmeta.setLore(lore_hunter);
        hunter_icon.setItemMeta(hunter_iconmeta);
        
		inv.setItem(0, soldier_icon);
		inv.setItem(1, hunter_icon);
		
		player.openInventory(inv);
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action a = event.getAction();
		ItemStack is = event.getItem();
		
		if(a == Action.PHYSICAL || is == null || is.getType()==Material.AIR)
			return;
		
		if(is.getType()==Material.NETHER_STAR)
			tsGUI(event.getPlayer());
		
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onMove(InventoryClickEvent event){
	    Player player = (Player) event.getWhoClicked();
	    
	    switch (event.getSlot()) {
	    case 0: // Select soldier
	    	if (Main.getClassPicked().getEntries().contains(event.getWhoClicked().getName())) {
	    		event.getWhoClicked().sendMessage(ChatColor.GRAY + "You may not change classes at this time.");
	    	}
	    	
	    	else {
	   
		    	Main.getClassSoldier().addEntry(event.getWhoClicked().getName());
		    	event.getWhoClicked().sendMessage(ChatColor.GRAY + "You selected the" + ChatColor.AQUA + " Soldier " + ChatColor.GRAY + "class.");
		    	player.closeInventory();
	    }
	    
	    	break;
	    
	    case 1: // Select hunter
	    	if (Main.getClassPicked().getEntries().contains(event.getWhoClicked().getName())) {
	    		event.getWhoClicked().sendMessage(ChatColor.GRAY + "You may not change classes at this time.");
	    	}
	    	
	    	else {
	    
		    	Main.getClassHunter().addEntry(event.getWhoClicked().getName());
		    	event.getWhoClicked().sendMessage(ChatColor.GRAY + "You selected the" + ChatColor.AQUA + " Hunter " + ChatColor.GRAY + "class.");
		    	player.closeInventory();
	    }
	
	    	break;
	    	
	    }
	    event.setCancelled(true);
	    
	   }
	     
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
	
	if (event.getItemDrop().getItemStack().getType() == Material.ENCHANTED_BOOK ||event.getItemDrop().getItemStack().getType() == Material.NETHER_STAR || event.getItemDrop().getItemStack().getType() == Material.BLAZE_POWDER ) {
		event.setCancelled(true);
	}
	}
	
	@EventHandler
	public void onPlayerOffHand(PlayerSwapHandItemsEvent event) {
		event.setCancelled(true);
	}
		
	}
