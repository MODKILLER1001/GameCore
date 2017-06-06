package net.warvale.core.stats;

import net.warvale.core.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatsEvents implements Listener {

    StatsEvents(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
	public void onJoin(PlayerJoinEvent e){
		final Player p = (Player) e.getPlayer();
		if(StatsManager.getKills(p) == null){
			StatsManager.setKills(p, 0);
		}
		if(StatsManager.getDeaths(p) == null){
			StatsManager.setDeaths(p, 0);
		}
		if(StatsManager.getWins(p) == null){
			StatsManager.setWins(p, 0);
		}
		if(StatsManager.getCoresBroken(p) == null){
			StatsManager.setCoresBroken(p, 0);
		}
		if(StatsManager.getKDR(p) == null){
			StatsManager.updateKDR(p);
		}
		if(StatsManager.getLongestSnipe(p) == null){
			StatsManager.setLongestSnipe(p, 0);
		}
		StatsManager.loadPlayer(p);
	}
    
    @EventHandler
	public void onQuit(PlayerQuitEvent e){
		StatsManager.savePlayer(e.getPlayer());
	}
    
    @EventHandler
    public void onDeathOrKill(PlayerDeathEvent event){
    	if(event.getEntity() instanceof Player){
    		if(event.getEntity().getKiller() instanceof Player){
    			Player killer = event.getEntity().getKiller();
    			Player victim = event.getEntity();
    			StatsManager.addKills(killer, 1);
    			StatsManager.addDeaths(victim, 1);
    			StatsManager.updateKDR(killer);
    			StatsManager.updateKDR(victim);
    		}
    	}
    }

}
