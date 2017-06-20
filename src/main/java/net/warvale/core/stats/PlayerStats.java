package net.warvale.core.stats;

import net.warvale.core.Main;
import net.warvale.core.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerStats {

    private UUID uuid;
    private int kills = 0;
    private int deaths = 0;
    private int killStreak = 0;
    private int totalKills = 0;
    private int totalDeaths = 0;
    private int highestKillStreak = 0;
    private int wins = 0;
    private int coresBroken = 0;
    private int longestSnipe = 0;

    public PlayerStats(UUID uuid) {
        this.uuid = uuid;
        if (Game.getInstance().isStatsEnabled()) {
            this.loadData();
        }
    }

    /**
     * Gets the uuid of the player
     *
     * @return the uuid of the player
     */
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * Gets the player
     *
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Gets the kd for the player
     *
     * @return the kd for the player
     */
    public double getKd() {
        double d = 0.0;
        d = this.totalKills > 0 && this.totalDeaths == 0 ? (double)this.totalKills : (this.totalKills == 0 && this.totalDeaths == 0 ? 0.0 : (double)(this.totalKills / this.totalDeaths));
        return d;
    }

    /**
     * Gets the name of the player
     *
     * @return the name of the player
     */
    public String getName() {
        return Bukkit.getServer().getOfflinePlayer(uuid).getName();
    }

    /**
     * Increments total deaths
     */
    public void addTotalDeath() {
        ++this.totalDeaths;
    }

    /**
     * Gets the total deaths
     *
     * @return total deaths
     */
    public int getTotalDeaths() {
        return this.totalDeaths;
    }

    /**
     * Sets the total deaths
     *
     * @param totalDeaths the total deaths
     */
    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    /**
     * Increments total kills
     */
    public void addTotalKill() {
        ++this.totalKills;
    }

    /**
     * Gets the total kills
     *
     * @return total kills
     */
    public int getTotalKills() {
        return this.totalKills;
    }

    /**
     * Sets the total kills
     *
     * @param totalKills the total kills
     */
    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    /**
     * Increments the kills for the current game
     */
    public void addKill() {
        ++this.kills;
    }

    /**
     * Gets the ammount of kills for the current game
     *
     * @return the ammount of kills for the current game
     */
    public int getKills() {
        return this.kills;
    }

    /**
     * Increments the deaths for the current game
     */
    public void addDeath() {
        ++this.deaths;
    }

    /**
     * gets the deaths for the current game
     *
     * @return the deaths for the current game
     */
    public int getDeaths() {
        return this.deaths;
    }

    /**
     * Gets the highest kill streak
     *
     * @return the highest kill streak
     */
    public int getHighestKillStreak() {
        return this.highestKillStreak;
    }

    /**
     * Sets the highest killstreak
     *
     * @param n the highest kill streak
     */
    public void setHighestKillStreak(int n) {
        this.highestKillStreak = n;
    }


    /**
     * Increments the kill streak
     */
    public void addKillStreak() {
        ++this.killStreak;
    }

    /**
     * Gets the kill streak for the current game
     *
     * @return the kill streak for the current game
     */
    public int getKillStreak() {
        return killStreak;
    }

    /**
     * Resets the kill streak
     */
    public void resetKillStreak() {
        this.killStreak = 0;
    }

    /**
     * Increments the wins
     */
    public void addWin() {
        ++this.wins;
    }

    /**
     * Gets the number of wins a player has
     * @return
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the number of wins a player has
     *
     * @param wins the number of wins a player has
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Increments the number of cores broken
     */
    public void addCoresBroken() {
        ++this.coresBroken;
    }

    /**
     * Gets the number of cores broken
     *
     * @return the number of cores broken
     */
    public int getCoresBroken() {
        return this.coresBroken;
    }

    /**
     * Sets the number of cores broken
     *
     * @param broken the number of cores broken
     */
    public void setCoresBroken(int broken) {
        this.coresBroken = broken;
    }

    /**
     * Increments the longest snipe
     */
    public void addLongestSnipe() {
        ++this.longestSnipe;
    }

    /**
     * Sets the longest snipe
     * @param longest the longist snipe
     */
    public void setLongestSnipe(int longest) {
        this.longestSnipe = longest;
    }

    /**
     * Gets the longest snipe
     * @return the longest snipe
     */
    public int getLongestSnipe() {
        return this.longestSnipe;
    }

    /**
     * Loads player stats from the database
     */
    private void loadData() {

        if (this.hasData()) {
            new BukkitRunnable(){

                @Override
                public void run() {

                    try {

                        PreparedStatement stmt = Main.getDB().getConnection().prepareStatement("SELECT * FROM `stats` WHERE `uuid` = ?;");
                        stmt.setString(1, PlayerStats.this.uuid.toString());
                        stmt.executeQuery();
                        ResultSet set = stmt.getResultSet();
                        if (set.next()) {

                            PlayerStats.this.setTotalKills(set.getInt("kills"));
                            PlayerStats.this.setTotalDeaths(set.getInt("deaths"));
                            PlayerStats.this.setHighestKillStreak(set.getInt("killstreak"));
                            PlayerStats.this.setWins(set.getInt("wins"));
                            PlayerStats.this.setCoresBroken(set.getInt("cores_broken"));
                            PlayerStats.this.setLongestSnipe(set.getInt("longest_snipe"));

                        }
                        set.close();
                        stmt.close();
                    } catch (SQLException ex) {
                        Main.get().getLogger().log(Level.SEVERE, "Could not load data for player: " + PlayerStats.this.getName(), ex);
                    }
                }

            }.runTaskAsynchronously(Main.get());
        } else {
            Main.get().getLogger().log(Level.INFO, "Creating data for player: " + this.getName());
            this.createData();
        }

    }

    /**
     * Creates default stats in the database
     */
    public void createData() {
        new BukkitRunnable(){

            @Override
            public void run() {

                try {

                    PreparedStatement stmt = Main.getDB().getConnection().prepareStatement("INSERT INTO `stats` " +
                            "(uuid, name, kills, deaths, killstreak, wins, cores_broken, longest_snipe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    stmt.setString(1, PlayerStats.this.uuid.toString());
                    stmt.setString(2, PlayerStats.this.getName());
                    stmt.setInt(3, PlayerStats.this.getTotalKills());
                    stmt.setInt(4, PlayerStats.this.getTotalDeaths());
                    stmt.setInt(5, PlayerStats.this.getHighestKillStreak());
                    stmt.setInt(6, PlayerStats.this.getWins());
                    stmt.setInt(7, PlayerStats.this.getCoresBroken());
                    stmt.setInt(8, PlayerStats.this.getLongestSnipe());
                    stmt.execute();
                    stmt.close();

                } catch (SQLException ex) {
                    Main.get().getLogger().log(Level.SEVERE, "Could not create data for player: " + PlayerStats.this.getName(), ex);
                }

            }

        }.runTaskAsynchronously(Main.get());
    }

    /**
     * Updates player stats in the database
     */
    public void saveData() {

        if (this.hasData()) {

            new BukkitRunnable(){

                @Override
                public void run() {

                    try {
                        PreparedStatement stmt = Main.getDB().getConnection().prepareStatement("SELECT * FROM `stats` WHERE `uuid` = ? LIMIT 1;");
                        stmt.setString(1, PlayerStats.this.uuid.toString());
                        ResultSet set = stmt.executeQuery();

                        if (set.next()) {
                            stmt.close();

                            stmt = Main.getDB().getConnection().prepareStatement("UPDATE `stats` SET name = ? WHERE uuid = ?;");
                            stmt.setString(1, PlayerStats.this.getName());
                            stmt.setString(2, PlayerStats.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = Main.getDB().getConnection().prepareStatement("UPDATE `stats` SET kills = ? WHERE uuid = ?;");
                            stmt.setInt(1, PlayerStats.this.getTotalKills());
                            stmt.setString(2, PlayerStats.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = Main.getDB().getConnection().prepareStatement("UPDATE `stats` SET deaths = ? WHERE uuid = ?;");
                            stmt.setInt(1, PlayerStats.this.getTotalDeaths());
                            stmt.setString(2, PlayerStats.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = Main.getDB().getConnection().prepareStatement("UPDATE `stats` SET killstreak = ? WHERE uuid = ?;");
                            stmt.setInt(1, PlayerStats.this.getHighestKillStreak());
                            stmt.setString(2, PlayerStats.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = Main.getDB().getConnection().prepareStatement("UPDATE `stats` SET wins = ? WHERE uuid = ?;");
                            stmt.setInt(1, PlayerStats.this.getWins());
                            stmt.setString(2, PlayerStats.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = Main.getDB().getConnection().prepareStatement("UPDATE `stats` SET cores_broken = ? WHERE uuid = ?;");
                            stmt.setInt(1, PlayerStats.this.getCoresBroken());
                            stmt.setString(2, PlayerStats.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = Main.getDB().getConnection().prepareStatement("UPDATE `stats` SET longest_snipe = ? WHERE uuid = ?;");
                            stmt.setInt(1, PlayerStats.this.getLongestSnipe());
                            stmt.setString(2, PlayerStats.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                        }

                    } catch (SQLException ex) {
                        Main.get().getLogger().log(Level.SEVERE, "Could not update data for player: " + PlayerStats.this.getName(), ex);
                    }

                }

            }.runTaskAsynchronously(Main.get());

        } else {
            Main.get().getLogger().log(Level.INFO, "Creating data for player: " + this.getName());
            this.createData();
        }

    }

    /**
     * Checks if stats exist for the player
     * @return true if stats exist, false otherwise
     */
    public boolean hasData() {
        try {
            PreparedStatement stmt = Main.getDB().getConnection().prepareStatement("SELECT `uuid` FROM `stats` WHERE `uuid` = ?;");
            stmt.setString(1, this.uuid.toString());
            stmt.executeQuery();
            ResultSet set = stmt.getResultSet();
            if (set.next()) {
                return true;
            }

        } catch (SQLException ex) {
        }
        return false;
    }

}
