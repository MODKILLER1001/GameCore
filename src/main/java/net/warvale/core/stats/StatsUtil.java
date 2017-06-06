package net.warvale.core.stats;

import java.util.Random;

import net.warvale.core.Main;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public class StatsUtil implements Listener {

    StatsUtil(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static int randomNum(int Low, int High){
		Random r = new Random();
		int R = r.nextInt(High-Low) + Low;
		return R;
	}

}
