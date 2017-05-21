package net.warvale.core.game.logic;


import net.warvale.core.Main;
import net.warvale.core.game.logic.BoardManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.logging.Level;

public class TeamManager {

    private final Scoreboard board;
    private final Main plugin;

    private Team blueTeam;
    private Team redTeam;
    private Team spectatorTeam;

    public TeamManager(Main plugin, BoardManager board) {
        this.plugin = plugin;
        this.board = board.getBoard();
    }

    public void setup() {

        blueTeam = board.registerNewTeam("blue");
        redTeam = board.registerNewTeam("red");
        spectatorTeam = board.registerNewTeam("spectator");

        redTeam.setAllowFriendlyFire(false);
        blueTeam.setAllowFriendlyFire(false);
        spectatorTeam.setAllowFriendlyFire(false);
        spectatorTeam.setCanSeeFriendlyInvisibles(true);

        blueTeam.setPrefix(ChatColor.DARK_AQUA.toString());
        redTeam.setPrefix(ChatColor.RED.toString());
        spectatorTeam.setPrefix(ChatColor.GRAY.toString());


        plugin.getLogger().log(Level.INFO, "Teams have been setup!");
    }

    public Team getBlueTeam() {
        return blueTeam;
    }

    public Team getRedTeam() {
        return redTeam;
    }

    public Team getSpectatorTeam() {
        return spectatorTeam;
    }

}
