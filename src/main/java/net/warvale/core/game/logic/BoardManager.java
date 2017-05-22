package net.warvale.core.game.logic;


import net.warvale.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class BoardManager {

    private final Main plugin;
    private final Scoreboard board;

    private Objective backup;
    private Objective game;
    private Objective lobby;

    public BoardManager(Main plugin) {
        this.plugin = plugin;

        board = Bukkit.getScoreboardManager().getMainScoreboard();

        backup = board.getObjective("backup");
        game = board.getObjective("game");
        lobby = board.getObjective("lobby");

    }

    /**
     * Setup the scoreboard objectives.
     */
    public void setup() {

        if (board.getObjective("backup") == null) {
            backup = board.registerNewObjective("backup", "dummy");
        }

        if (board.getObjective("game") == null) {
            game = board.registerNewObjective("game", "dummy");
        }

        if (board.getObjective("lobby") == null) {
            lobby = board.registerNewObjective("lobby", "dummy");
        }

        game.setDisplaySlot(DisplaySlot.SIDEBAR);
        lobby.setDisplaySlot(DisplaySlot.SIDEBAR);

        plugin.getLogger().info("Scoreboards has been setup.");
    }

    /**
     * Get the main scoreboard.
     *
     * @return The scoreboard.
     */
    public Scoreboard getBoard() {
        return board;
    }

    /**
     * Get the sidebar game backup objective.
     *
     * @return The objective.
     */
    public Objective getBackupObjective() {
        return backup;
    }

    /**
     * Get the sidebar game objective.
     *
     * @return The objective.
     */
    public Objective getGameObjective() {
        return game;
    }

    /**
     * Get the sidebar lobby objective.
     *
     * @return The objective.
     */
    public Objective getLobbyObjective() {
        return lobby;
    }

    /**
     * Gets a score for the given string.
     *
     * @param entry the wanted string.
     * @return The score of the string.
     */
    public int getScore(final String entry) {
        return game.getScore(entry).getScore();
    }

    /**
     * Gets a actual score for the given string.
     *
     * @param entry the wanted string.
     * @return The actual score of the string.
     */
    public int getActualScore(final String entry) {
        return backup.getScore(entry).getScore();
    }

    /**
     * Reset the score of the given string.
     *
     * @param entry the string resetting.
     */
    public void resetScore(final String entry) {
        int score = getActualScore(entry);

        board.resetScores(entry);

        // this should never be reset
        for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
            if (offline.getName().equalsIgnoreCase(entry)) {
                backup.getScore(entry).setScore(score);
                break;
            }
        }
    }

}
