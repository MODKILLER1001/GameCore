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
	        
	        event.getPlayer().getInventory().setItem(2, classselect);
	        }
	    }
	}
	
	private void tsGUI(Player player ) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Select a class:");
		
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
