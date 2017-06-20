package net.warvale.core.game.logic;

import net.warvale.core.Main;
import net.warvale.core.utils.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Set;


/**
 * Created by AAces on 6/19/2017.
 */
public class TeamBalancing {
    public static void balanceTeams(){
        Team blue = Main.getTeams().getBlueTeam();
        Team red = Main.getTeams().getRedTeam();
        ArrayList<String> teamBlue = new ArrayList<>();
        ArrayList<String> teamRed = new ArrayList<>();
        for (String player : Main.getTeams().getBlueTeam().getEntries()){
            teamBlue.add(player);
        }
        for (String player : Main.getTeams().getRedTeam().getEntries()){
            teamRed.add(player);
        }
        int blueSize = teamBlue.size();
        int redSize = teamRed.size();

        if ((blueSize - 1) > redSize){
            double x = blueSize - redSize;
            if (x%2 == 0){
                x = x/2;
            } else {
                x = (x/2) + 0.5;
            }
            for (double i = x; i > 0; i--){
                int random = NumberUtils.random(blueSize, 1);
                blue.removeEntry(teamBlue.get(random));
                red.addEntry(teamBlue.get(random));
            }
        } else if ((redSize - 1) > blueSize){
            double x = redSize - blueSize;
            if (x%2 == 0){
                x = x/2;
            } else {
                x = (x/2) + 0.5;
            }
            for (double i = x; i > 0; i--){
                int random = NumberUtils.random(redSize, 1);
                red.removeEntry(teamRed.get(random));
                blue.addEntry(teamRed.get(random));
            }
        }
    }
}
