package warvale.core.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import warvale.core.plugin.chat.BroadcastType;
import warvale.core.plugin.classes.Class;
import warvale.core.plugin.commands.Version;
import warvale.core.plugin.commands.Join;
import warvale.core.plugin.commands.Leave;
import warvale.core.plugin.commands.StartAuto;
import warvale.core.plugin.connect.JoinServer;
import warvale.core.plugin.connect.LeaveServer;
import warvale.core.plugin.events.GlobalEvent;
import warvale.core.plugin.spec.ClassSelect;
import warvale.core.plugin.spec.Preferences;
import warvale.core.plugin.spec.TeamSelect;
import warvale.core.plugin.utils.NumberUtils;

import java.util.Arrays;

public class Main extends JavaPlugin implements Listener {

  	private static Team blueTeam;
  	private static Team redTeam;
  	private static Team spectatorTeam;

  	private static Main instance;


	@Override
    public void onEnable() {
		instance = this;

    	new Class("Soldier", 0,
				Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&aDefault class. &7Charges forward and deals extra"),
				ChatColor.translateAlternateColorCodes('&', "&7damage to enemies.")),
				new ItemStack(Material.FIREWORK_CHARGE), "Charge");
		new Class("Archer", 0,
				Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&aDefault class. &7Shoot flaming arrows towards"),
				ChatColor.translateAlternateColorCodes('&', "&7enemies to deal extra damage.")),
				new ItemStack(Material.MAGMA_CREAM), "Volley");
		new Class("Assassin", 0,
				Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&aDefault class. &7Hide in the shadows with your"),
				ChatColor.translateAlternateColorCodes('&', "&7sneaky invisibility.")),
				new ItemStack(Material.SULPHUR), "Sneak");
		new Class("Miner", 100,
				Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&aPurchasable class. &7Chance to deal double damage"),
				ChatColor.translateAlternateColorCodes('&', "&7when mining the core.")),
				new ItemStack(Material.IRON_PICKAXE), "Superswing");

    	getCommand("join").setExecutor(new Join());
    	getCommand("leave").setExecutor(new Leave());
    	getCommand("start").setExecutor(new StartAuto(this));
    	getCommand("gamever").setExecutor(new Version());

		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

		blueTeam = board.registerNewTeam("blue");
        redTeam = board.registerNewTeam("red");
        spectatorTeam = board.registerNewTeam("spectator");

    	redTeam.setAllowFriendlyFire(false);
    	blueTeam.setAllowFriendlyFire(false);
    	spectatorTeam.setAllowFriendlyFire(false);
    	spectatorTeam.setCanSeeFriendlyInvisibles(true);

    	new JoinServer(this);
    	new LeaveServer(this);
    	new GlobalEvent(this);
    	new TeamSelect(this);
    	new ClassSelect(this);
    	new Preferences(this);

        blueTeam.setPrefix(ChatColor.DARK_AQUA.toString());
    	redTeam.setPrefix(ChatColor.RED.toString());
    	spectatorTeam.setPrefix(ChatColor.GRAY.toString());

    	for (BroadcastType type : BroadcastType.values()) {
    		type.autoBroadcast(NumberUtils.random(100, 1), NumberUtils.random(7000, 6000));
		}
    }

    @Override
    public void onDisable() {

       	blueTeam.unregister();
        redTeam.unregister();
        spectatorTeam.unregister();

        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Warvale: Conquest Gamecore " + ChatColor.GRAY + "Reloading plugin...");
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

    public static Main get() {
		return instance;
	}

}