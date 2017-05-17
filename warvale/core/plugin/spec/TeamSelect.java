package warvale.core.plugin.spec;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
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
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;

import net.md_5.bungee.api.ChatColor;
import warvale.core.plugin.Main;

public class TeamSelect implements Listener {
	
    public TeamSelect(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static Inventory inv;
	
    @EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
	    if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
	        ItemStack teamselect = new ItemStack(Material.ENCHANTED_BOOK, 1); {
	        ItemMeta spawnmeta = teamselect.getItemMeta();
	        spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Team Selector");
	        teamselect.setItemMeta(spawnmeta);
	        
	        event.getPlayer().getInventory().setItem(4, teamselect);
	        }
	    }
	}
	
	private void tsGUI(Player player ) {
		inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Select a team:");
		
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
		
		ItemStack closemenu = new ItemStack(Material.BARRIER, 1);
		ItemMeta closemenumeta = closemenu.getItemMeta();
		closemenumeta.setDisplayName(ChatColor.DARK_RED + "Close selector");
		closemenu.setItemMeta(closemenumeta);
		
		inv.setItem(3, itemtsred);
		inv.setItem(4, closemenu);
		inv.setItem(5, itemtscyan);
		
		player.openInventory(inv);
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action a = event.getAction();
		ItemStack is = event.getItem();
		
		if(a == Action.PHYSICAL || is == null || is.getType()==Material.AIR)
			return;
		
		if(is.getType()==Material.ENCHANTED_BOOK)
			tsGUI(event.getPlayer());
		
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST) 
	public void onMove(InventoryClickEvent event){
	    Player player = (Player) event.getWhoClicked();

	    if (!event.getInventory().equals(inv)) {
	    	if (Main.getSpectatorTeam().getEntries().contains(event.getWhoClicked().getName())) {
	    		event.setCancelled(true);
	    	return;
		}
	    }
	    switch (event.getSlot()) {
	    case 3: // Join red 
		    if (event.getInventory().equals(inv)) {
		    	Main.getRedTeam().addEntry(event.getWhoClicked().getName());
					event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
				    for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
				    	event.getWhoClicked().removePotionEffect(effect.getType());
				    	((Player) event.getWhoClicked()).setAllowFlight(false);
				    	player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
				    	player.closeInventory();
				    	player.getInventory().clear();
			    }
	    	break;
	    case 4: // Close menu
	    	if (event.getInventory().equals(inv)) {
		    	event.setCancelled(true);
		    	player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_CLOSE, 1, 1);
		    	player.closeInventory();
	    	}
	    	break;
	    case 5: // Join blue
		    if (event.getInventory().equals(inv)) {
		    	Main.getBlueTeam().addEntry(event.getWhoClicked().getName());
					event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
				    for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
				    	event.getWhoClicked().removePotionEffect(effect.getType());
				    	((Player) event.getWhoClicked()).setAllowFlight(false);
				    	player.playSound(player.getLocation(), Sound.BLOCK_ENDERCHEST_OPEN, 1, 1);
				    	player.closeInventory();
				    	player.getInventory().clear();
			    }
	    	break;
	    	
	    default:
	    	if (Main.getSpectatorTeam().getEntries().contains(event.getWhoClicked().getName())) {
	    		event.setCancelled(true);
	        	break;
	        }
	    }
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

