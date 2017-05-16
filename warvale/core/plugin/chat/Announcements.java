package warvale.core.plugin.chat;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import warvale.core.plugin.Main;

public class Announcements implements Listener {

	private Main main;
	public Announcements(Main main) {
		this.main = main;
	}

	public void autobroadcast() {
		final String[] messages = {
				ChatColor.GOLD + "Advertisement 1 example",
				ChatColor.GREEN + "Advertisement 2 example"
		};
		Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(Arrays.asList(messages).get(new Random().nextInt(messages.length)));
			}
		}, 0, 100); 
	}
	
}