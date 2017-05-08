package warvale.core.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import warvale.core.plugin.commands.CTF;
import warvale.core.plugin.commands.Join;
import warvale.core.plugin.commands.Kits;
import warvale.core.plugin.commands.Leave;
import warvale.core.plugin.commands.StartAuto;
import warvale.core.plugin.events.WorldEvent;
import warvale.core.plugin.kits.KitItems;

public class Main extends JavaPlugin {
	  
  	private static Team blueTeam;
  	private static Team redTeam;
  	private static Team spectatorTeam;

    @Override
    public void onEnable() {
    	
    	new warvale.core.plugin.JoinServer(this);
    	new WorldEvent(this);
    	getCommand("join").setExecutor(new Join());
    	getCommand("leave").setExecutor(new Leave());
    	getCommand("ctf").setExecutor(new CTF());
    	getCommand("start").setExecutor(new StartAuto(this));
    	getCommand("kits").setExecutor(new Kits());
    	getCommand("kit").setExecutor(new KitItems());
    	

		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
            
      	blueTeam = board.registerNewTeam("blue");
        redTeam = board.registerNewTeam("red");
        spectatorTeam = board.registerNewTeam("spectator");
        
    	redTeam.setAllowFriendlyFire(false);
    	blueTeam.setAllowFriendlyFire(false);
    	spectatorTeam.setAllowFriendlyFire(false);
    	spectatorTeam.setCanSeeFriendlyInvisibles(true);
      	
        blueTeam.setPrefix(ChatColor.DARK_AQUA.toString());
    	redTeam.setPrefix(ChatColor.RED.toString());
    	spectatorTeam.setPrefix(ChatColor.GRAY.toString());
    }
   
    @Override
    public void onDisable() {
       	blueTeam.unregister();
        redTeam.unregister();
        spectatorTeam.unregister();

        Bukkit.broadcastMessage(ChatColor.DARK_RED + "CTF " + ChatColor.GRAY + "Reloading plugin...");
    }
  
  	public static Team getBlueTeam() {
      	return blueTeam;
  	}
  
 	public static Team getRedTeam() {
        return redTeam;
    }
 	
 	public static Team getSpectatorTeam() {
        return spectatorTeam;
    }

}