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

    public void addScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("game", "dummy");

        objective.setDisplayName(ChatColor.DARK_GRAY + "» " + ChatColor.DARK_RED + "Warvale"
                + ChatColor.DARK_GRAY + " «" );
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team time = scoreboard.registerNewTeam("GameTime");
        time.addEntry("§cTime:");
        time.setSuffix(" §7");

        Team playerCount = scoreboard.registerNewTeam("PlayerCount");
        playerCount.addEntry("§cPlayers:");
        playerCount.setSuffix(" §7");

        scoreboards.put(player.getUniqueId(), scoreboard);
    }


    public void removeScoreboard(Player player) {
        if (scoreboards.containsKey(player.getUniqueId())) {
            scoreboards.get(player.getUniqueId()).clearSlot(DisplaySlot.SIDEBAR);
            scoreboards.remove(player.getUniqueId());
        }
    }

    public void shutdown() {
        for (Scoreboard scoreboard : scoreboards.values()) {
            for (Objective objective : scoreboard.getObjectives()) {
                objective.unregister();
            }
            scoreboard.clearSlot(DisplaySlot.SIDEBAR);
        }
        scoreboards.clear();
    }

    public void newScoreboard(Player p) {

        //add the user to the scoreboard
        if (!getScoreboards().containsKey(p.getUniqueId())) {
            addScoreboard(p);
        }

        //update the scoreboard
        updateTime();
        updatePlayerCount();

        p.setScoreboard(getScoreboards().get(p.getUniqueId()));
    }

    public void updateTime() {

        for (Player online : Bukkit.getOnlinePlayers()) {

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team time = getScoreboards().get(online.getUniqueId()).getTeam("GameTime");
            if (objective != null && time != null) {
                objective.getScore("    ").setScore(14);
                //objective.getScore("§8» §cTime:").setScore(7);

                time.setSuffix(" §7"+ convert(GameRunnable.getSeconds()));

                objective.getScore("§cTime:").setScore(13);
                objective.getScore("   ").setScore(12);

            }

        }

    }

    public void updatePlayerCount() {

        for (Player online : Bukkit.getOnlinePlayers()) {

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("game");
            Team playerCount = getScoreboards().get(online.getUniqueId()).getTeam("PlayerCount");
            if (objective != null && playerCount != null) {

                playerCount.setSuffix(" §7"+ Bukkit.getOnlinePlayers().size() + "/" + Game.getInstance().getMaxPlayer());

                objective.getScore("§cPlayers:").setScore(11);
                objective.getScore("  ").setScore(10);
            }

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
