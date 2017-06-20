package net.warvale.core.game.start;

import net.warvale.core.Main;
import net.warvale.core.game.MatchInfo;
import net.warvale.core.maps.GameMap;
import net.warvale.core.tasks.BossbarCountdownTask;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by AAces on 6/2/2017.
 */
public class GameStart {
    public static boolean votingActive = false;
    public static ArrayList<String> voted = new ArrayList<>();
    public static ArrayList<Player> teamBlue = new ArrayList<>();
    public static ArrayList<Player> teamRed = new ArrayList<>();
    public static ArrayList<Player> inGame = new ArrayList<>();
    public static HashMap<String, Integer> votes = new HashMap<>();
    public static HashMap<Integer, String> mapNumbers = new HashMap<>();
    public static boolean initActive = false;
    public static GameMap map;
    public static MatchInfo info = new MatchInfo(teamBlue.size() + teamRed.size(), teamRed.size(), teamBlue.size());


    public void startCountdown() { //runs when there is at least one player on each team (change in TeamSelect lines 113 and 133)
        map = null;

        GameMap.doMaps();

        mapNumbers.put(1, "Redwood Forest");
        mapNumbers.put(2, "Volcano Island");
        mapNumbers.put(3, "Pagoda Everglade");
        mapNumbers.put(4, "Extraterrestrial");
        mapNumbers.put(5, "Canyon Brook");

        votes.put("redwood_forest", 0);
        votes.put("volcano_island", 0);
        votes.put("pagoda_everglade", 0);
        votes.put("extraterrestrial", 0);
        votes.put("canyon_brook", 0);


        initActive = true;

        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            if (player.isOp()){
                player.sendMessage(ChatColor.DARK_RED + "[STAFF]" + ChatColor.WHITE + " The game is starting!");
            }
        }

        votingActive = true;
        voted.clear();

        //Get team players
        for (String player : Main.getTeams().getBlueTeam().getEntries()){
            teamBlue.add(Bukkit.getPlayer(player));
        }
        for (String player : Main.getTeams().getRedTeam().getEntries()){
            teamRed.add(Bukkit.getPlayer(player));
        }

        //send voting message
        for (int i = 0; i < teamBlue.size(); i++){
            Player p = teamBlue.get(i);
            p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "/vote" + ChatColor.BLUE.toString() + ChatColor.BOLD + " to vote for a map!");
        }
        for (int i = 0; i < teamRed.size(); i++) {
            Player p = teamRed.get(i);
            p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "/vote" + ChatColor.BLUE.toString() + ChatColor.BOLD + " to vote for a map!");
        }
        inGame.addAll(teamBlue);
        inGame.addAll(teamRed);
        info = new MatchInfo(inGame.size(), teamRed.size(), teamBlue.size());
        new BossbarCountdownTask().runTaskTimer(Main.get(), 0, 20);
    }




    public static void stopCountdown(StopReason reason){
        map = null;
        BarManager.getAnnounceBar().setVisible(false);
        mapNumbers.put(1, "Redwood Forest");
        mapNumbers.put(2, "Volcano Island");
        mapNumbers.put(3, "Pagoda Everglade");
        mapNumbers.put(4, "Extraterrestrial");
        mapNumbers.put(5, "Canyon Brook");

        votes.put("redwood_forest", 0);
        votes.put("volcano_island", 0);
        votes.put("pagoda_everglade", 0);
        votes.put("extraterrestrial", 0);
        votes.put("canyon_brook", 0);


        initActive = false;

        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            if (player.isOp()){
                player.sendMessage(ChatColor.DARK_RED + "[STAFF]" + ChatColor.WHITE + " The countdown has been stopped!");
            }
        }

        votingActive = false;
        voted.clear();

        for (int i = 0; i < teamBlue.size(); i++) {
            Player p = teamBlue.get(i);
            if (reason == StopReason.PLAYER_LEFT) {
                p.sendMessage(ChatColor.GRAY + "A player has left the game, and there are no longer enough players!");
            } else if (reason == StopReason.MODERATOR_STOPPED){
                p.sendMessage(ChatColor.GRAY + "A moderator has stopped the countdown!");
            }
        }
        for (int i = 0; i < teamRed.size(); i++) {
            Player p = teamRed.get(i);
            if (reason == StopReason.PLAYER_LEFT) {
                p.sendMessage(ChatColor.GRAY + "A player has left the game, and there are no longer enough players!");
            } else if (reason == StopReason.MODERATOR_STOPPED){
                p.sendMessage(ChatColor.GRAY + "A moderator has stopped the countdown!");
            }
        }

        new BossbarCountdownTask().cancel();
    }

}
