package warvale.core.plugin;

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
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class JoinServer implements Listener {

    public JoinServer(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
  
    @EventHandler

    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.getPlayer().getInventory().clear();
        event.getPlayer().sendMessage(ChatColor.GRAY + "Welcome back to " + ChatColor.DARK_RED + "Warvale!");
        Main.getSpectatorTeam().addEntry(event.getPlayer().getName());
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
        
        if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
        	event.getPlayer().setAllowFlight(true);
            ItemStack teamselect = new ItemStack(Material.ENCHANTED_BOOK, 1); {
            ItemMeta spawnmeta = teamselect.getItemMeta();
            spawnmeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + "Team Selector");
            teamselect.setItemMeta(spawnmeta);
            
            event.getPlayer().getInventory().setItem(4, teamselect);
            }
        }
    }
    
    private void tsGUI(Player player ) {
    	Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Select a team:");
    	
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
        
        switch (event.getSlot()) {
        case 3: // Join red 
        	Main.getRedTeam().addEntry(event.getWhoClicked().getName());
  			event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.RED + "red");
  		    for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
  		    	event.getWhoClicked().removePotionEffect(effect.getType());
  		    	((Player) event.getWhoClicked()).setAllowFlight(false);
  		    	player.closeInventory();
  		    	player.getInventory().clear();
        	break;
        case 4: // Close menu
        	event.setCancelled(true);
        	player.closeInventory();
        	break;
        case 5: // Join blue
        	Main.getBlueTeam().addEntry(event.getWhoClicked().getName());
  			event.getWhoClicked().sendMessage(ChatColor.GRAY + "You joined team " + ChatColor.DARK_AQUA + "blue");
  		    for (PotionEffect effect : event.getWhoClicked().getActivePotionEffects())
  		    	event.getWhoClicked().removePotionEffect(effect.getType());
  		    	((Player) event.getWhoClicked()).setAllowFlight(false);
  		    	player.closeInventory();
  		    	player.getInventory().clear();
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

