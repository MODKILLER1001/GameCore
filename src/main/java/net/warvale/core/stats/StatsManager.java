package net.warvale.core.stats;

import net.warvale.core.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsManager {

    private static StatsManager instance;
    private Map<UUID, PlayerStats> players = new HashMap<>();

    public static StatsManager getInstance() {
        if (instance == null) {
            instance = new StatsManager();
        }
        return instance;
    }

    public Map<UUID, PlayerStats> getPlayers() {
        return this.players;
    }

    public PlayerStats getPlayer(UUID playerUUID) {
        return this.players.get(playerUUID);
    }

    public boolean doesPlayerExsist(UUID playerUUID) {
        return this.players.containsKey(playerUUID);
    }

    public void createPlayer(UUID playerUUID) {
        this.players.put(playerUUID, new PlayerStats(playerUUID));
    }

    public void removePlayer(UUID playerUUID) {
        if (this.players.containsKey(playerUUID)) {

            if (Game.getInstance().isStatsEnabled()) {
                getPlayer(playerUUID).saveData();
            }

            this.players.remove(playerUUID);
        }
    }


}
