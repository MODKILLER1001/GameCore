package net.warvale.core.game.logic;

import net.warvale.core.Main;
import net.warvale.core.utils.NumberUtils;
import org.bukkit.scoreboard.Team;

import java.util.Set;


/**
 * Created by AAces on 6/19/2017.
 */
public class TeamBalancing {
    public static void balanceTeams(){
        Team blue = Main.getTeams().getBlueTeam();
        Team red = Main.getTeams().getRedTeam();
        int blueSize = blue.getSize();
        int redSize = red.getSize();



        if ((blueSize - 1) > redSize){
            double x = blueSize - redSize;
            if (x%2 == 0){
                x = x/2;
            } else {
                x = (x/2) + 0.5;
            }
            for (double i = x; i > 0; i--){
                int t = 1;
                int random = NumberUtils.random(blueSize, 1);
                for (String player : blue.getEntries()){
                    if (t == random){
                        blue.removeEntry(player);
                        red.addEntry(player);
                    } else {
                        t += 1;
                    }
                }
            }
        } else if ((redSize - 1) > blueSize){
            double x = redSize - blueSize;
            if (x%2 == 0){
                x = x/2;
            } else {
                x = (x/2) + 0.5;
            }
            for (double i = x; i > 0; i--){
                int t = 1;
                int random = NumberUtils.random(redSize, 1);
                for (String player : red.getEntries()){
                    if (t == random){
                        red.removeEntry(player);
                        blue.addEntry(player);
                    } else {
                        t += 1;
                    }
                }
            }
        }
    }
}
