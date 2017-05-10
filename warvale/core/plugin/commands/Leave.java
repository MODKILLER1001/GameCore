package warvale.core.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;
import warvale.core.plugin.Main;

public class Leave implements CommandExecutor {

	public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
		if (cs instanceof Player) {
          	Player sender = (Player) cs;
	        if (command.getName().equalsIgnoreCase("leave")) {
	        	if (Main.getRedTeam().getEntries().contains(sender.getName())) {
	        		sender.sendMessage(ChatColor.GRAY + "You may not leave the game at this time."); // Temporarily disabling leaving
	        	/*Main.getSpectatorTeam().addEntry(sender.getName());
	        		sender.sendMessage(ChatColor.GRAY + "Left the game on team " + ChatColor.RED + "red");
	    	    	sender.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
	    	    	sender.getInventory().clear();
	    	    	sender.setAllowFlight(true); */
	        	}
	        	
	        	else if (Main.getBlueTeam().getEntries().contains(sender.getName())) {
	        		sender.sendMessage(ChatColor.GRAY + "You may not leave the game at this time."); // Temporarily disabling leaving
	        		/*Main.getSpectatorTeam().addEntry(sender.getName());
	        		sender.sendMessage(ChatColor.GRAY + "Left the game on team " + ChatColor.DARK_AQUA + "blue");
	    	    	sender.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
	    	    	sender.getInventory().clear();
	    	    	sender.setAllowFlight(true);*/
	        	}
	        	
	        	else
	        		sender.sendMessage(ChatColor.GRAY + "You must be on a team to leave the game.");
	        	
	        	/*Main.getBlueTeam().removeEntry(sender.getName());
	        	Main.getRedTeam().removeEntry(sender.getName());*/

	            return true;
        }
        return false;
    }
		return false;
   
	}
}
