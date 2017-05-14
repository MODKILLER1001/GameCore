package warvale.core.plugin.connect;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import net.md_5.bungee.api.ChatColor;
import warvale.core.plugin.Main;

public class LeaveServer implements Listener {

    public LeaveServer(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
  
    @EventHandler

    public void onPlayerLeaveEvent(PlayerQuitEvent event) {
    	
    	String playerName = event.getPlayer().getName();
        event.getPlayer().getInventory().clear();
        
        event.setQuitMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " left.");      
     
        }
        }

    


