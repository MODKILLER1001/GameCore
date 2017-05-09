package warvale.core.plugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
    @EventHandler(priority = EventPriority.LOWEST) 
    public void onMove(InventoryClickEvent e){
        ItemStack i = e.getWhoClicked().getInventory().getItem(4);
        if(i != null)
        {
            if(e.getSlot() == 4 && i.getType() == Material.ENCHANTED_BOOK) // Get if the clicked slot was the 4th
            {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {

    if (event.getItemDrop().getItemStack().getType() == Material.ENCHANTED_BOOK) {
    event.setCancelled(true);
    }
    }
     }
