package net.warvale.core.game;


import net.warvale.core.Main;
import net.warvale.core.config.ConfigManager;
import net.warvale.core.game.logic.TeamManager;
import net.warvale.core.game.start.Initialization;
import net.warvale.core.maps.GameMap;
import net.warvale.core.utils.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

/**
 * Created by Draem on 5/20/2017.
 */
public class Game {

    private static boolean running = false;
    private static Game instance;
    private int maxPlayer;
    private int minPlayers;
    private int gameLength;
    private GameMap chosenmap;

    public static  Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private State state;

    public void setup() {
        try {
            state = State.valueOf(ConfigManager.getConfig().getString("state"));
        } catch (Exception e) {
            Bukkit.getLogger().warning("Setting the state to LOBBY as it can't find the saved one!");
            state = State.LOBBY;
        }

        maxPlayer = ConfigManager.getConfig().getInt("maxPlayers", 50);
        minPlayers = ConfigManager.getConfig().getInt("minPlayers", 8);
        gameLength = ConfigManager.getConfig().getInt("gameLength", 60);
    }

    /**
     * Set the current state.
     *
     * @param state The new state.
     */
    public void setState(State state) {
        this.state = state;

        ConfigManager.getConfig().set("state", state.name());
        ConfigManager.getInstance().saveConfig();
    }

    /**
     * Check if the current state is the same as the given state.
     *
     * @param state The state checking.
     * @return True if it's the same, false otherwise.
     */
    public boolean isState(State state) {
        return getState() == state;
    }

    /**
     * Gets the current game state.
     *
     * @return The current state.
     */
    public State getState() {
        return state;
    }

    public static boolean isRunning() {
        return running;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getGameLength() {
        return gameLength;
    }

    public void setChosenmap(GameMap map) {
        this.chosenmap = map;
    }

    public GameMap getChosenmap() {
        return chosenmap;
    }

}
