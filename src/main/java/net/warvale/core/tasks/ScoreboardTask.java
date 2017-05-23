package net.warvale.core.tasks;


import net.warvale.core.game.scoreboards.LobbyScoreboard;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardTask extends BukkitRunnable {

    private static ScoreboardTask instance;

    public static ScoreboardTask getInstance() {
        if (instance == null) {
            instance = new ScoreboardTask();
        }
        return instance;
    }

    @Override
    public void run() {

        LobbyScoreboard.getInstance().newLobbyBoard();

    }

}
