package net.warvale.core.game.end;

import net.warvale.core.Main;
import net.warvale.core.embers.EmberManager;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.stats.StatsManager;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.scoreboard.Team;

import java.sql.SQLException;

/**
 * Created by AAces on 6/6/2017.
 */
public class GameEnd {

    private static ChatColor colorChat;
    private static BarColor colorBar;
    private static Team losingTeam;
    private static Team redTeam = Main.getTeams().getRedTeam();
    private static Team blueTeam = Main.getTeams().getBlueTeam();
    private static Team spectatorTeam = Main.getTeams().getSpectatorTeam();

    public static void coreBrokenEnd(Team winningTeam){
        if (winningTeam == blueTeam){
            colorChat = ChatColor.DARK_AQUA;
            colorBar = BarColor.BLUE;
            losingTeam = redTeam;
        }
        if (winningTeam == redTeam){
            colorChat = ChatColor.RED;
            colorBar = BarColor.RED;
            losingTeam = blueTeam;
        }
        if (winningTeam == redTeam || winningTeam == blueTeam) {
            MessageManager.broadcast(PrefixType.MAIN, colorChat + winningTeam.getDisplayName() + ChatColor.GRAY + " has won the game!");
            BarManager.broadcast(colorBar, ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + colorChat + winningTeam.getDisplayName() + ChatColor.GRAY + " has won the game!");
            BarManager.getAnnounceBar().setVisible(true);
            BarManager.getAnnounceBar().setProgress(1);

            for (String p : winningTeam.getEntries()) {
                try {
                    EmberManager.giveEmbers(Bukkit.getPlayer(p), 500);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                StatsManager.addWins(Bukkit.getPlayer(p), 1);
                Bukkit.getPlayer(p).teleport(new Location(Bukkit.getWorld("lobby"), 383, 103, 71, 180, 0));
                Bukkit.getPlayer(p).sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "You hae been moved to the spectators team. Use " + ChatColor.RED + "/join <team>" + ChatColor.GRAY + " to play again!");
                winningTeam.removeEntry(p);
                spectatorTeam.addEntry(p);
            }
            for (String p : losingTeam.getEntries()) {
                Bukkit.getPlayer(p).teleport(new Location(Bukkit.getWorld("lobby"), 383, 103, 71, 180, 0));
                Bukkit.getPlayer(p).sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "You hae been moved to the spectators team. Use " + ChatColor.RED + "/join <team>" + ChatColor.GRAY + " to play again!");
                losingTeam.removeEntry(p);
                spectatorTeam.addEntry(p);
            }
        } else {
            System.out.println("Invalid Team Won");
        }
    }


    public static void tieEnd(){
        MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "The game has ended in a tie!");
        BarManager.broadcast(BarColor.PURPLE, ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "The game has ended in a tie!");
        BarManager.getAnnounceBar().setVisible(true);
        BarManager.getAnnounceBar().setProgress(1);
        for (String p : blueTeam.getEntries()){
            Bukkit.getPlayer(p).teleport(new Location(Bukkit.getWorld("lobby"), 383, 103, 71, 180, 0));
            Bukkit.getPlayer(p).sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "You hae been moved to the spectators team. Use " + ChatColor.RED + "/join <team>" + ChatColor.GRAY + " to play again!");
            blueTeam.removeEntry(p);
            spectatorTeam.addEntry(p);
        }
        for (String p : redTeam.getEntries()){
            Bukkit.getPlayer(p).teleport(new Location(Bukkit.getWorld("lobby"), 383, 103, 71, 180, 0));
            Bukkit.getPlayer(p).sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "Warvale" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "You hae been moved to the spectators team. Use " + ChatColor.RED + "/join <team>" + ChatColor.GRAY + " to play again!");
            redTeam.removeEntry(p);
            spectatorTeam.addEntry(p);
        }
    }

}
