package net.warvale.core.stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import net.warvale.core.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class StatsManager implements Listener{
	
	public static StatsSQL mysql;
	
	public StatsSQL getMySQL(){
	     return mysql;
	}
	
	public StatsManager(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        mysql = new StatsSQL(Main.getDB().getHostname(), "" + Main.getDB().getPort(), Main.getDB().getDatabase(), Main.getDB().getUser(), Main.getDB().getPassword());
        PreparedStatement stats = mysql.prepareStatement("CREATE TABLE IF NOT EXISTS stats(UUID varchar(36) NOT NULL, NAME VARCHAR(16) NOT NULL, KILLS INT(20) NOT NULL, DEATHS INT(20) NOT NULL, WINS INT(20) NOT NULL, CORES_BROKEN INT(20) NOT NULL, KDR INT(20) NOT NULL, LONGEST_SNIPE INT(20) NOT NULL, PRIMARY KEY(UUID))");
    
        mysql.update(stats);
	}

	public static HashMap<UUID, Integer> Kills = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> Deaths = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> Wins = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> CoresBroken = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> KDR = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> LongestSnipe = new HashMap<UUID, Integer>();

    private static final String INSERT = "INSERT INTO stats VALUES(?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE NAME=?";
    private static final String SELECT = "SELECT * FROM stats WHERE UUID=?";
    private static final String SAVE = "UPDATE stats SET KILLS=?, DEATHS=?, WINS=?, CORES_BROKEN=?, KDR=?, LONGEST_SNIPE=? WHERE UUID=?";
    
    private static void addPlayer(Player p, Integer kills, Integer deaths, Integer wins, Integer coresBroken, Integer kdr, Integer longestSnipe) {
        removePlayer(p);
        Kills.put(p.getUniqueId(), kills);
        Deaths.put(p.getUniqueId(), deaths);
        Wins.put(p.getUniqueId(), wins);
        CoresBroken.put(p.getUniqueId(), coresBroken);
        KDR.put(p.getUniqueId(), kdr);
        LongestSnipe.put(p.getUniqueId(), longestSnipe);
    }

    public static void removePlayer(Player p) {
    	Kills.remove(p.getUniqueId());
        Deaths.remove(p.getUniqueId());
        Wins.remove(p.getUniqueId());
        CoresBroken.remove(p.getUniqueId());
        KDR.remove(p.getUniqueId());
        LongestSnipe.remove(p.getUniqueId());
    }

    public static Integer getKills(Player p) {
        if (Kills.containsKey(p.getUniqueId())){
            return Kills.get(p.getUniqueId());
        }
        return null;
    }
    
    public static Integer setKills(Player p, Integer kills) {
        if (Kills.containsKey(p.getUniqueId())){
        	Kills.replace(p.getUniqueId(), kills);
        }
        return null;
    }
    
    public static Integer addKills(Player p, Integer kills) {
        if (Kills.containsKey(p.getUniqueId())){
        	if(kills >= 0){
        		Kills.replace(p.getUniqueId(), Kills.get(p.getUniqueId()) + kills);
        	}
        }
        return null;
    }
    
    public static Integer removeKills(Player p, Integer kills) {
        if (Kills.containsKey(p.getUniqueId())){
        	if(kills >= 0){
        		Kills.replace(p.getUniqueId(), Kills.get(p.getUniqueId()) - kills);
        	}
        }
        return null;
    }
    
    public static Integer getDeaths(Player p) {
        if (Deaths.containsKey(p.getUniqueId())){
            return Deaths.get(p.getUniqueId());
        }
        return null;
    }
    
    public static Integer setDeaths(Player p, Integer deaths) {
        if (Deaths.containsKey(p.getUniqueId())){
        	Deaths.replace(p.getUniqueId(), deaths);
        }
        return null;
    }
    
    public static Integer addDeaths(Player p, Integer deaths) {
        if (Deaths.containsKey(p.getUniqueId())){
        	if(deaths >= 0){
        		Deaths.replace(p.getUniqueId(), Deaths.get(p.getUniqueId()) + deaths);
        	}
        }
        return null;
    }
    
    public static Integer removeDeaths(Player p, Integer deaths) {
        if (Deaths.containsKey(p.getUniqueId())){
        	if(deaths >= 0){
        		Deaths.replace(p.getUniqueId(), Deaths.get(p.getUniqueId()) - deaths);
        	}
        }
        return null;
    }
    
    public static Integer getWins(Player p) {
        if (Wins.containsKey(p.getUniqueId())){
            return Wins.get(p.getUniqueId());
        }
        return null;
    }
    
    public static Integer setWins(Player p, Integer wins) {
        if (Wins.containsKey(p.getUniqueId())){
        	Wins.replace(p.getUniqueId(), wins);
        }
        return null;
    }
    
    public static Integer addWins(Player p, Integer wins) {
        if (Wins.containsKey(p.getUniqueId())){
        	if(wins >= 0){
        		Wins.replace(p.getUniqueId(), Wins.get(p.getUniqueId()) + wins);
        	}
        }
        return null;
    }
    
    public static Integer removeWins(Player p, Integer wins) {
        if (Wins.containsKey(p.getUniqueId())){
        	if(wins >= 0){
        		Wins.replace(p.getUniqueId(), Wins.get(p.getUniqueId()) - wins);
        	}
        }
        return null;
    }
    
    public static Integer getCoresBroken(Player p) {
        if (CoresBroken.containsKey(p.getUniqueId())){
            return CoresBroken.get(p.getUniqueId());
        }
        return null;
    }
    
    public static Integer setCoresBroken(Player p, Integer coreAmount) {
        if (CoresBroken.containsKey(p.getUniqueId())){
        	CoresBroken.replace(p.getUniqueId(), coreAmount);
        }
        return null;
    }
    
    public static Integer addCoresBroken(Player p, Integer coreAmount) {
        if (CoresBroken.containsKey(p.getUniqueId())){
        	if(coreAmount >= 0){
        		CoresBroken.replace(p.getUniqueId(), CoresBroken.get(p.getUniqueId()) + coreAmount);
        	}
        }
        return null;
    }
    
    public static Integer removeCoresBroken(Player p, Integer coreAmount) {
        if (CoresBroken.containsKey(p.getUniqueId())){
        	if(coreAmount >= 0){
        		CoresBroken.replace(p.getUniqueId(), CoresBroken.get(p.getUniqueId()) - coreAmount);
        	}
        }
        return null;
    }
    
    public static Integer getKDR(Player p) {
        if (KDR.containsKey(p.getUniqueId())){
            return KDR.get(p.getUniqueId());
        }
        return null;
    }
    
    public static void updateKDR(Player p) {
        if (Kills.containsKey(p.getUniqueId()) && Deaths.containsKey(p.getUniqueId())){
            KDR.replace(p.getUniqueId(), Kills.get(p.getUniqueId()) / Deaths.get(p.getUniqueId()));
        }
    }
    
    public static Integer getLongestSnipe(Player p) {
        if (LongestSnipe.containsKey(p.getUniqueId())){
            return LongestSnipe.get(p.getUniqueId());
        }
        return null;
    }
    
    public static Integer setLongestSnipe(Player p, Integer snipeAmount) {
        if (LongestSnipe.containsKey(p.getUniqueId())){
        	LongestSnipe.replace(p.getUniqueId(), snipeAmount);
        }
        return null;
    }
    
    public static Integer addLongestSnipe(Player p, Integer snipeAmount) {
        if (LongestSnipe.containsKey(p.getUniqueId())){
        	if(snipeAmount >= 0){
        		LongestSnipe.replace(p.getUniqueId(), LongestSnipe.get(p.getUniqueId()) + snipeAmount);
        	}
        }
        return null;
    }
    
    public static Integer removeLongestSnipe(Player p, Integer snipeAmount) {
        if (LongestSnipe.containsKey(p.getUniqueId())){
        	if(snipeAmount >= 0){
        		LongestSnipe.replace(p.getUniqueId(), LongestSnipe.get(p.getUniqueId()) - snipeAmount);
        	}
        }
        return null;
    }
    
    public static void loadPlayer(final Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.get(), new Runnable() {
            public void run() {
                try {
                    PreparedStatement statement = mysql.prepareStatement(INSERT);
                    statement.setString(1, p.getUniqueId().toString());
                    statement.setString(2, p.getName());
                    statement.setInt(3, Kills.get(p.getUniqueId()));
                    statement.setInt(4, Deaths.get(p.getUniqueId()));
                    statement.setInt(5, Wins.get(p.getUniqueId()));
                    statement.setInt(6, CoresBroken.get(p.getUniqueId()));
                    statement.setInt(7, KDR.get(p.getUniqueId()));
                    statement.setInt(8, LongestSnipe.get(p.getUniqueId()));
                    statement.setString(9, p.getName());
                    mysql.update(statement);

                    statement = mysql.prepareStatement(SELECT);
                    statement.setString(1, p.getUniqueId().toString());
                    ResultSet result = mysql.query(statement);
                    if (result.next())
                        addPlayer(p, result.getInt("KILLS"), result.getInt("DEATHS"), result.getInt("WINS"), result.getInt("CORES_BROKEN"), result.getInt("KDR"), result.getInt("LONGEST_SNIPE"));
                    result.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void savePlayer(final Player p) {
    	try {
    		PreparedStatement statement = mysql.prepareStatement(SAVE);
            statement.setInt(1, Kills.get(p.getUniqueId()));
            statement.setInt(2, Deaths.get(p.getUniqueId()));
            statement.setInt(3, Wins.get(p.getUniqueId()));
            statement.setInt(4, CoresBroken.get(p.getUniqueId()));
            statement.setInt(5, KDR.get(p.getUniqueId()));
            statement.setInt(6, LongestSnipe.get(p.getUniqueId()));
            statement.setString(7, p.getUniqueId().toString());
            mysql.update(statement);
            removePlayer(p);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public static void onDisableSavePlayer() {
    	for(Player p : Bukkit.getOnlinePlayers()){
    		try {
    			if(Kills.containsKey(p.getUniqueId()) 
    			&& (Deaths.containsKey(p.getUniqueId())) 
    			&& (Wins.containsKey(p.getUniqueId()) 
    			&& (CoresBroken.containsKey(p.getUniqueId())) 
    			&& (KDR.containsKey(p.getUniqueId())) 
    			&& (LongestSnipe.containsKey(p.getUniqueId())))){
    				//SPACER
    				//******
    				//SPACER
    				PreparedStatement statement = mysql.prepareStatement(SAVE);
                    statement.setInt(1, Kills.get(p.getUniqueId()));
                    statement.setInt(2, Deaths.get(p.getUniqueId()));
                    statement.setInt(3, Wins.get(p.getUniqueId()));
                    statement.setInt(4, CoresBroken.get(p.getUniqueId()));
                    statement.setInt(5, KDR.get(p.getUniqueId()));
                    statement.setInt(6, LongestSnipe.get(p.getUniqueId()));
                    statement.setString(7, p.getUniqueId().toString());
                    mysql.update(statement);
                    removePlayer(p);
                }
    		} catch (SQLException e) {
    			e.printStackTrace();
            }
        }
    }
}
