package net.warvale.core.tasks;


import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.scoreboards.GameScoreboard;
import net.warvale.core.game.scoreboards.LobbyScoreboard;
import net.warvale.staffcore.StaffCore;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {

            if (Game.getInstance().isState(State.COUNTDOWN)) {
                if (BossbarCountdownTask.getCountdown() >= 60) {
                    LobbyScoreboard.getInstance().newScoreboard(player, ChatColor.RED + "Warvale");
                } else {
                    LobbyScoreboard.getInstance().newScoreboard(player, ChatColor.WHITE + "Starting in " + ChatColor.GREEN +
                    BossbarCountdownTask.getCountdown() + " seconds");
                }
            } else if (Game.getInstance().isState(State.INGAME)) {
                GameScoreboard.getInstance().newScoreboard(player);
            } else {
                LobbyScoreboard.getInstance().newScoreboard(player);
            }
        }
    }

}
