package net.warvale.core.game.scoreboards;

import net.warvale.core.Main;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.logic.BoardManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LobbyScoreboard {

    private static LobbyScoreboard instance;

    private Team time;
    private Team lobbyCount;

    public static LobbyScoreboard getInstance() {
        if (instance == null) {
            instance = new LobbyScoreboard();
        }
        return instance;
    }


    public void setup() {
        time = Main.getBoard().getBoard().registerNewTeam("LobbyTime");
        time.addEntry("§8» §cTime:");
        time.setSuffix(" §7");

        lobbyCount = Main.getBoard().getBoard().registerNewTeam("LobbyCount");
        lobbyCount.addEntry("§8» §cPlayers:");
        lobbyCount.setSuffix(" §7");
    }

    public void newLobbyBoard() {

        if (!Game.getInstance().isState(State.LOBBY)) {
            return;
        }

        Main.getBoard().getLobbyObjective().setDisplayName(ChatColor.GREEN + "Waiting for players...");

        updateTime();
        updatePlayerCount();

    }

    public void updateTime() {

        Objective objective = Main.getBoard().getLobbyObjective();
        objective.getScore("§8§a---------------§c").setScore(14);

        Format date = new SimpleDateFormat("HH:mm:ss 'UTC'", Locale.US);
        String dateStr = date.format(new Date());
        time.setSuffix(" §7"+ dateStr);

        objective.getScore("§8» §cTime:").setScore(13);

    }

    public void updatePlayerCount() {

        Objective objective = Main.getBoard().getLobbyObjective();
        objective.getScore("§8§b---------------§c").setScore(12);

        lobbyCount.setSuffix(" §7" + Bukkit.getOnlinePlayers().size() + "/" + Game.getInstance());

        objective.getScore("§8» §cPlayers:").setScore(11);

    }

    public void shutdown() {
        time.unregister();
        lobbyCount.unregister();
    }
}
