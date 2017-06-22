package net.warvale.core.game.scoreboards;

import com.google.common.collect.Maps;
import net.warvale.core.Main;
import net.warvale.core.game.CoreBlock;
import net.warvale.core.game.Game;
import net.warvale.core.game.logic.GameRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class GameScoreboard {

    private static GameScoreboard instance;

    public static GameScoreboard getInstance() {
        if (instance == null) {
            instance = new GameScoreboard();
        }
        return instance;
    }

    //scoreboard map
    private Map<UUID, Scoreboard> scoreboards = Maps.newHashMap();

    public Map<UUID, Scoreboard> getScoreboards() {
        return scoreboards;
    }
    String timeSbCache = "";
    String redSbCache = "";
    String blueSbCache = "";
    public void addScoreboard(Player player) {
        // Set up the scoreboard
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("game", "dummy");
        double minTime = GameRunnable.getSeconds() / 60;
        objective.setDisplayName(ChatColor.DARK_RED + "Warvale: " + ChatColor.RED + "Conquest");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore("✊").setScore(-2); // doesnt render in minecraft
        objective.getScore(ChatColor.DARK_RED + "mc.warvale.net").setScore(-9);

        scoreboards.put(player.getUniqueId(), scoreboard);
        new BukkitRunnable() {

            @Override
            public void run() {

                // Update time
                scoreboard.resetScores(timeSbCache);
                String timenow = ChatColor.AQUA + "Time: " + ChatColor.BLUE + convert(GameRunnable.getSeconds());
            objective.getScore(timenow).setScore(-1);
                timeSbCache = timenow;


                // update red

                scoreboard.resetScores(redSbCache);
                String redNow = ChatColor.RED + "Red: " + ChatColor.BLUE + new CoreBlock().getRedCoreHealth();
                objective.getScore(redNow).setScore(-3);
                redSbCache = redNow;

                // update blue

                scoreboard.resetScores(blueSbCache);
                String blueNow = ChatColor.BLUE + "Blue: " + ChatColor.GREEN + new CoreBlock().getBlueCoreHealth();
                objective.getScore(blueNow).setScore(-4);
                blueSbCache = blueNow;
            }
        }.runTaskTimer(Main.get(), 10, 1);



    }



    @Deprecated
    public void removeScoreboard(Player player) {
        // I have no idea if this works, no need for it I think.
        if (scoreboards.containsKey(player.getUniqueId())) {
            scoreboards.get(player.getUniqueId()).clearSlot(DisplaySlot.SIDEBAR);
            scoreboards.remove(player.getUniqueId());
        }
    }







    private String convert(int n) {
        int n2 = n / 3600;
        int n3 = n - n2 * 3600;
        int n4 = n3 / 60;
        int n5 = n3 - n4 * 60;
        String string = "";
        if (n2 < 10) {
            string = string + "0";
        }
        string = string + n2 + ":";
        if (n4 < 10) {
            string = string + "0";
        }
        string = string + n4 + ":";
        if (n5 < 10) {
            string = string + "0";
        }
        string = string + n5;
        return string;
    }


}
