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
import warvale.core.plugin.spec.ClassSelect;
import warvale.core.plugin.spec.Preferences;
import warvale.core.plugin.spec.TeamSelect;

public class Main extends JavaPlugin {
	  
  	private static Team blueTeam;
  	private static Team redTeam;
  	private static Team spectatorTeam;
  	
  	private static Team classPicked;
  	
  	private static Team soldierClassTeam;
  	private static Team hunterClassTeam;

    @Override
    public void onEnable() {
    	
    	new JoinServer(this);
    	new WorldEvent(this);
    	new TeamSelect(this);
    	new ClassSelect(this);
    	new Preferences(this);
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
        
        soldierClassTeam = board.registerNewTeam("soldier");
        hunterClassTeam = board.registerNewTeam("hunter");
        
        classPicked = board.registerNewTeam("classpicked");
        
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
        
        soldierClassTeam.unregister();
        hunterClassTeam.unregister();
        
        classPicked.unregister();

        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Warvale: Gamecore " + ChatColor.GRAY + "Reloading plugin...");
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
 	
 	public static Team getClassSoldier() {
 		return soldierClassTeam;
 	}
 	
 	public static Team getClassHunter() {
 		return hunterClassTeam;
 	}
 	
 	public static Team getClassPicked() {
 		return classPicked;
 	}

}