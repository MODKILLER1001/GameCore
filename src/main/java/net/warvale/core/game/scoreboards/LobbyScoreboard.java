package net.warvale.core.game.scoreboards;

import com.google.common.collect.Maps;
import net.warvale.core.Main;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.logic.BoardManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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

public class LobbyScoreboard {

    private static LobbyScoreboard instance;

    public static LobbyScoreboard getInstance() {
        if (instance == null) {
            instance = new LobbyScoreboard();
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
        Objective objective = scoreboard.registerNewObjective("lobby", "dummy");

        objective.setDisplayName(ChatColor.DARK_GRAY + "» " + ChatColor.GREEN + "Waiting For Players..."
                + ChatColor.DARK_GRAY + " «" );
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team time = scoreboard.registerNewTeam("LobbyTime");
        time.addEntry("§8» §cTime:");
        time.setSuffix(" §7");

        Team lobbyCount = scoreboard.registerNewTeam("LobbyCount");
        lobbyCount.addEntry("§8» §cPlayers:");
        lobbyCount.setSuffix(" §7");

        scoreboards.put(player.getUniqueId(), scoreboard);
    }

    public void addScoreboard(Player player, String display) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("lobby", "dummy");

        objective.setDisplayName(ChatColor.DARK_GRAY + "» " + display + ChatColor.DARK_GRAY + " «" );
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Team time = scoreboard.registerNewTeam("LobbyTime");
        time.addEntry("§8» §cTime:");
        time.setSuffix(" §7");

        Team lobbyCount = scoreboard.registerNewTeam("LobbyCount");
        lobbyCount.addEntry("§8» §cPlayers:");
        lobbyCount.setSuffix(" §7");

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

    public void newScoreboard(Player p, String display) {

        //add the user to the scoreboard
        if (!getScoreboards().containsKey(p.getUniqueId())) {
            addScoreboard(p, display);
        }

        Objective objective = getScoreboards().get(p.getUniqueId()).getObjective("lobby");

        if (objective != null) {
            objective.setDisplayName(ChatColor.DARK_GRAY + "» " + display + ChatColor.DARK_GRAY + " «");
        }

        //update the scoreboard
        updateTime();
        updatePlayerCount();

        p.setScoreboard(getScoreboards().get(p.getUniqueId()));
    }

    public void updateTime() {

        for (Player online : Bukkit.getOnlinePlayers()) {

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("lobby");
            Team time = getScoreboards().get(online.getUniqueId()).getTeam("LobbyTime");
            if (objective != null && time != null) {
                objective.getScore("    ").setScore(14);
                //objective.getScore("§8» §cTime:").setScore(7);

                Format date = new SimpleDateFormat("HH:mm:ss 'UTC'", Locale.US);
                String dateStr = date.format(new Date());
                time.setSuffix(" §7"+ dateStr);

                objective.getScore("§8» §cTime:").setScore(13);
                objective.getScore("   ").setScore(12);

            }

        }

    }

    public void updatePlayerCount() {

        for (Player online : Bukkit.getOnlinePlayers()) {

            Objective objective = getScoreboards().get(online.getUniqueId()).getObjective("lobby");
            Team playerCount = getScoreboards().get(online.getUniqueId()).getTeam("LobbyCount");
            if (objective != null && playerCount != null) {

                playerCount.setSuffix(" §7"+ Bukkit.getOnlinePlayers().size() + "/" + Game.getInstance().getMaxPlayer());

                objective.getScore("§8» §cPlayers:").setScore(11);
                objective.getScore("  ").setScore(10);
            }

        }

    }


}
