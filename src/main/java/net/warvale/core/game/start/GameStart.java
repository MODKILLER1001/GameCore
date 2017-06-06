package net.warvale.core.game.start;

import net.warvale.core.Main;
import net.warvale.core.game.MatchInfo;
import net.warvale.core.map.GameMap;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.tasks.BossbarCountdownTask;
import net.warvale.core.utils.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.*;


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

        mapNumbers.put(1, "Redwood Forest");
        mapNumbers.put(2, "Volcano Island");
        mapNumbers.put(3, "Pagoda Everglade");
        mapNumbers.put(4, "Extraterrestrial");

        votes.put("redwood_forest", 0);
        votes.put("volcano_island", 0);
        votes.put("pagoda_everglade", 0);
        votes.put("extraterrestrial", 0);


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
            p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "/vote #" + ChatColor.BLUE.toString() + ChatColor.BOLD + " to vote for a map!" + ChatColor.DARK_BLUE +
                    "\n1: Redwood Forest" +
                    "\n2: Volcano Island" +
                    "\n3: Pagoda Everglade" +
                    "\n4: Extraterrestrial");
        }
        for (int i = 0; i < teamRed.size(); i++) {
            Player p = teamRed.get(i);
            p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "/vote #" + ChatColor.BLUE.toString() + ChatColor.BOLD + " to vote for a map!" + ChatColor.DARK_BLUE +
                    "\n1: Redwood Forest" +
                    "\n2: Volcano Island" +
                    "\n3: Pagoda Everglade" +
                    "\n4: Extraterrestrial");
        }
        inGame.addAll(teamBlue);
        inGame.addAll(teamRed);
        info = new MatchInfo(inGame.size(), teamRed.size(), teamBlue.size());
        new BossbarCountdownTask().runTaskTimer(Main.get(), 0, 20);
    }


    public static void voteTally(){
        votingActive = false;
        int rf = votes.get("redwood_forest");
        int vi = votes.get("volcano_island");
        int pe = votes.get("pagoda_everglade");
        int et = votes.get("extraterrestrial");

        int n = NumberUtils.random(4,1);

        switch (n){
            case 1:
                if (rf >= vi && rf >= pe && rf >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(1));
                } else if (vi >= rf && vi >= pe && vi >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(2));
                } else if (pe >= rf && pe >= vi && pe >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(3));
                } else if (et >= rf && et >= vi && et >= pe){
                    map = GameMap.getMaps().get(mapNumbers.get(4));
                } else {
                    map = GameMap.getMaps().get(mapNumbers.get(NumberUtils.random(4, 1)));
                }
                break;
            case 2:
                if (vi >= rf && vi >= pe && vi >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(2));
                } else if (pe >= rf && pe >= vi && pe >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(3));
                } else if (et >= rf && et >= vi && et >= pe){
                    map = GameMap.getMaps().get(mapNumbers.get(4));
                } else if (rf >= vi && rf >= pe && rf >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(1));
                } else {
                    map = GameMap.getMaps().get(mapNumbers.get(NumberUtils.random(4, 1)));
                }
                break;
            case 3:
                if (pe >= rf && pe >= vi && pe >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(3));
                } else if (et >= rf && et >= vi && et >= pe){
                    map = GameMap.getMaps().get(mapNumbers.get(4));
                } else if (rf >= vi && rf >= pe && rf >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(1));
                } else if (vi >= rf && vi >= pe && vi >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(2));
                } else {
                    map = GameMap.getMaps().get(mapNumbers.get(NumberUtils.random(4, 1)));
                }
                break;
            case 4:
                if (et >= rf && et >= vi && et >= pe){
                    map = GameMap.getMaps().get(mapNumbers.get(4));
                } else if (rf >= vi && rf >= pe && rf >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(1));
                } else if (vi >= rf && vi >= pe && vi >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(2));
                } else if (pe >= rf && pe >= vi && pe >= et){
                    map = GameMap.getMaps().get(mapNumbers.get(3));
                } else {
                    map = GameMap.getMaps().get(mapNumbers.get(NumberUtils.random(4, 1)));
                }
                break;

        }
        MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "Voting is now closed!");
    }



}
