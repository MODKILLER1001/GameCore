package warvale.core.plugin.connect;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.md_5.bungee.api.ChatColor;
import warvale.core.plugin.Main;

public class JoinServer implements Listener {

    public JoinServer(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
  
    @EventHandler

    public void onPlayerJoinEvent(PlayerJoinEvent event) {
    	
    	String playerName = event.getPlayer().getName();
        event.getPlayer().getInventory().clear();
        event.getPlayer().sendMessage(ChatColor.GRAY + "Welcome back to " + ChatColor.DARK_RED + "Warvale!");
        Main.getSpectatorTeam().addEntry(event.getPlayer().getName());
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
        
        event.setJoinMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " joined.");
        
        if (Main.getSpectatorTeam().getEntries().contains(event.getPlayer().getName())) {
        	event.getPlayer().setAllowFlight(true);
     
}
    }
}

