package net.warvale.core;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;

import net.warvale.core.chat.BroadcastType;
import net.warvale.core.classes.Class;
import net.warvale.core.classes.abilities.AbilityManager;
import net.warvale.core.commands.CommandHandler;
import net.warvale.core.config.ConfigManager;
import net.warvale.core.connect.JoinServer;
import net.warvale.core.connect.LeaveServer;
import net.warvale.core.connect.PingListener;
import net.warvale.core.game.CoreBlock;
import net.warvale.core.game.Game;
import net.warvale.core.game.logic.BoardManager;
import net.warvale.core.game.logic.RegionProtection;
import net.warvale.core.game.logic.TeamManager;
import net.warvale.core.game.scoreboards.LobbyScoreboard;
import net.warvale.core.map.ConquestMap;
import net.warvale.core.maps.GameMap;
import net.warvale.core.message.MessageManager;
import net.warvale.core.spec.ClassSelect;
import net.warvale.core.spec.Preferences;
import net.warvale.core.spec.TeamSelect;
import net.warvale.core.stats.StatsEvents;
import net.warvale.core.stats.StatsManager;
import net.warvale.core.stats.StatsUtil;
import net.warvale.core.tasks.LobbyTask;
import net.warvale.core.tasks.ScoreboardTask;
import net.warvale.core.utils.ftp.AbstractFileConnection;
import net.warvale.core.utils.mc.items.EnchantGlow;
import net.warvale.core.utils.mc.menu.Menu;
import net.warvale.core.utils.sql.SQLConnection;
import net.warvale.core.utils.NumberUtils;
import net.warvale.core.utils.files.PropertiesFile;
import net.warvale.core.utils.sql.SQLConnection;

import net.warvale.core.utils.sql.SQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

public class Main extends JavaPlugin implements Listener {

	private static final Random random = new Random();
	private static boolean shutdown = false;
  	private static Main instance;

  	//sql stuff
	private final File sqlpropertiesfile = new File("connect.properties");
	private final File filePropertiesFile = new File("ftp.properties");
	private PropertiesFile propertiesFile;
	private PropertiesFile fileProperties;
	private static SQLConnection db;

	//scoreboard stuff
	private static BoardManager board;
	private static TeamManager teams;

	//command stuff
	private static CommandHandler commandHandler;

	//map dir
	private static File mapDir = new File("Maps");

	//file stuff
	private AbstractFileConnection fileConnection;

	//menus
	private Set<Menu> registeredMenus = new HashSet<>();

	@Override
    public void onEnable() {
		instance = this;

		setupClasses();

		ConfigManager.getInstance().setup();
		MessageManager.getInstance().setup();

		board = new BoardManager(this);
		board.setup();

		teams = new TeamManager(this, board);
		teams.setup();

		Game.getInstance().setup();

    	new JoinServer(this);
    	new LeaveServer(this);
    	new GlobalEvent(this);
    	new TeamSelect(this);
    	new ClassSelect(this);
    	new Preferences(this);
    	Bukkit.getPluginManager().registerEvents(new PingListener(), this);
    	Bukkit.getPluginManager().registerEvents(new StatsEvents(this), this);
    	Bukkit.getPluginManager().registerEvents(new StatsManager(this), this);
    	Bukkit.getPluginManager().registerEvents(new StatsUtil(this), this);
    	Bukkit.getPluginManager().registerEvents(new CoreBlock(), this);



    	for (BroadcastType type : BroadcastType.values()) {
    		switch (type) {
				case TIP:
					BroadcastType.autoBroadcastTip(NumberUtils.random(100, 1), NumberUtils.random(7000, 6000));
					break;
				case ADVERTISEMENT:
					BroadcastType.autoBroadcastAdvertisement(NumberUtils.random(100, 1), NumberUtils.random(7000, 6000));
					break;
    		}

		}

		//register commands
		commandHandler = new CommandHandler(this);
    	commandHandler.registerCommands();


		/* Register AbilityManager */

		Bukkit.getServer().getPluginManager().registerEvents(new AbilityManager(), this);
    	//register scoreboards
		ScoreboardTask.getInstance().runTaskTimer(this, 0, 20);
		LobbyTask.getInstance().runTaskTimer(this, 0, 20);

		//load the maps

		GameMap.registerMap(new ConquestMap("Redwood_Forest"));
		GameMap.registerMap(new ConquestMap("Pagoda_Everglade"));
		GameMap.registerMap(new ConquestMap("Volcano_Island"));
		GameMap.registerMap(new ConquestMap("Extraterrestrial"));
		GameMap.registerMap(new ConquestMap("Canyon_Brook"));

		Bukkit.getPluginManager().registerEvents(new RegionProtection(), this);
		
		//Load online players stats if any are on
		for(Player p : Bukkit.getOnlinePlayers()){
			StatsManager.loadPlayer(p);
		}

		EnchantGlow.getGlow();

    }

    @Override
    public void onLoad() {

		//Loading properties
		getLogger().log(Level.INFO, "Loading SQL Properties");

		if (!sqlpropertiesfile.exists()) {
			try {
				PropertiesFile.generateFresh(sqlpropertiesfile, new String[]{"hostname", "port", "username", "password", "database"}, new String[]{"localhost", "3306", "root", "NONE", "crnetwork"});
			} catch (Exception e) {
				getLogger().log(Level.WARNING, "Could not generate fresh properties file");
			}
		}

		try {
			propertiesFile = new PropertiesFile(sqlpropertiesfile);
		} catch (Exception e) {
			getLogger().log(Level.SEVERE, "Could not load SQL properties file", e);
			endSetup("Exception occurred when loading properties");
		}

		String temp;
		getLogger().log(Level.INFO, "Finding database information...");

		//SQL info
		try {
			db = new SQLConnection(propertiesFile.getString("hostname"), propertiesFile.getNumber("port").intValue(), propertiesFile.getString("database"), propertiesFile.getString("username"), (temp = propertiesFile.getString("password")).equals("NONE") ? null : temp);
		} catch (ParseException ex) {
			getLogger().log(Level.WARNING, "Could not load database information", ex);
			endSetup("Invalid database port");
		} catch (Exception ex) {
			getLogger().log(Level.WARNING, "Could not load database information", ex);
			endSetup("Invalid configuration");
		}

		//Connecting to MySQL
		getLogger().log(Level.INFO, "Connecting to MySQL...");

		try {
			getDB().openConnection();
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not connect to MySQL", e);
			endSetup("Could not establish connection to database");
		}

		if (!mapDir.exists()) {
			mapDir.mkdir();
		}

	}

    @Override
    public void onDisable() {
    	
    	//save players stats
    	StatsManager.onDisableSavePlayer();

		//unregister teams
		getTeams().getBlueTeam().unregister();
		getTeams().getRedTeam().unregister();
		getTeams().getSpectatorTeam().unregister();

		//unregister scoreboard specific teams
		LobbyScoreboard.getInstance().shutdown();

		getLogger().log(Level.INFO, "Closing connection to database...");

		try {
			getDB().closeConnection();
		} catch (SQLException e) {
			getLogger().log(Level.SEVERE, "Could not close database connection", e);
		}

		try {
			if (getFileConnection().isConnected()) {
				getFileConnection().close();
			}
		} catch (Exception ex) {
			getLogger().log(Level.SEVERE, "Could not close FTP connection", ex);
		}

        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Warvale: Conquest Gamecore " + ChatColor.GRAY + "Reloading plugin...");

		unregisterMenus();
    }

    public static Main get() {
		return instance;
	}



	private static void setupClasses() {
		new Class("Soldier", 0,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aDefault class. &7Charges forward and deals extra"),
						ChatColor.translateAlternateColorCodes('&', "&7damage to enemies.")),
				new ItemStack(Material.FIREWORK_CHARGE), "Charge");
		new Class("Archer", 0,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&', "&aDefault class. &7Shoot explosive arrows towards"),
						ChatColor.translateAlternateColorCodes('&', "&7enemies to deal extra damage.")),
				new ItemStack(Material.MAGMA_CREAM), "Volley");
		new Class("Assassin", 0,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&', "&aDefault class. &7Hide in the shadows with your"),
						ChatColor.translateAlternateColorCodes('&', "&7sneaky invisibility.")),
				new ItemStack(Material.SULPHUR), "Sneak");
		new Class("Miner", 100,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Chance to deal double damage"),
						ChatColor.translateAlternateColorCodes('&', "&7when mining the core.")),
				new ItemStack(Material.IRON_PICKAXE), "Superswing");
		new Class("Spy", 500,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Appear as if you are on the other team"),
						ChatColor.translateAlternateColorCodes('&', "&7 by transforming your name color!")),
				new ItemStack(Material.GLASS_BOTTLE), "Undercover");
		new Class("Technician", 1000,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Paralyze your enemy with your stunning "),
						ChatColor.translateAlternateColorCodes('&', "&7electrocution powers.")),
				new ItemStack(Material.REDSTONE_TORCH_ON), "Electrocute");
		new Class("Musician", 1000,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Play your music and gain healing powers"),
						ChatColor.translateAlternateColorCodes('&', "&7when near it!")),
				new ItemStack(Material.RECORD_8), "Jukebox");
		new Class("Pyromaniac", 1000,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Use the power of fire to light your"),
						ChatColor.translateAlternateColorCodes('&', "&7target aflame.")),
				new ItemStack(Material.FIREBALL), "Ignite");
		new Class("Necromancer", 5000,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Raise evil mobs from the dead to attack"),
						ChatColor.translateAlternateColorCodes('&', "&7enemies.")),
				new ItemStack(Material.BONE), "Reincarnate");
		new Class("Earthbender", 5000,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Harness the powers of the Earth to control"),
						ChatColor.translateAlternateColorCodes('&', "&7the environment.")),
				new ItemStack(Material.GRASS), "Terraform");
		new Class("Medic", 7000,
				Arrays.asList(
						ChatColor.translateAlternateColorCodes('&',
								"&aPurchasable class. &7Take on the role of a support class by "),
						ChatColor.translateAlternateColorCodes('&', "&7healing your teammates around you!")),
				new ItemStack(Material.BLAZE_ROD), "Trainquility");
	}

	public static SQLConnection getDB() {
		return db;
	}

	public PropertiesFile getProperties() {
		return propertiesFile;
	}

	public void endSetup(String s) {
		getLogger().log(Level.SEVERE, s);
		if (!shutdown) {
			stop();
			shutdown = true;
		}
		throw new IllegalArgumentException("Disabling... " + s);
	}

	private void stop() {
		Bukkit.getServer().shutdown();
	}

	public static BoardManager getBoard() {
		return board;
	}

	public static TeamManager getTeams() {
		return teams;
	}

	public static File getMapDir() {
		return mapDir;
	}

	public PropertiesFile getFileProperties() {
		return fileProperties;
	}

	public AbstractFileConnection getFileConnection() {
		return fileConnection;
	}

	public net.warvale.core.maps.GameMap loadMap(String name) {
		return new ConquestMap(name);
	}


	public static Random getRandom() {
		return random;
	}

	public void registerMenu(Menu menu) {
		if (registeredMenus.contains(menu)) {
			throw new IllegalArgumentException("Menu already registered");
		}
		registeredMenus.add(menu);
	}

	public void unregisterMenu(Menu menu) {
		if (registeredMenus.contains(menu)) {
			registeredMenus.remove(menu);
		} else {
			throw new IllegalArgumentException("Menu not registered");
		}
	}

	public void unregisterMenus() {
		registeredMenus.clear();
	}

	public Set<Menu> listMenu() {
		return new HashSet<>(registeredMenus);
	}

	public void loadMapsTable() throws SQLException, ClassNotFoundException {

		//check if the maps table exists
		if (!SQLUtil.tableExists(getDB(), "maps")) {

			//create the maps table
			getDB().executeSQL(
					"CREATE TABLE `maps` (" +
							"`id` BIGINT NOT NULL AUTO_INCREMENT, " +
							"`map` VARCHAR(255) NOT NULL," +
							"`key` TEXT NOT NULL," +
							"`value` TEXT NOT NULL," +
							"PRIMARY KEY (`id`)," +
							"INDEX `map_key` (`map`)" +
							");");

			//log successful createion
			getLogger().log(Level.INFO, "Maps table created successfully!");

		}

	}
}