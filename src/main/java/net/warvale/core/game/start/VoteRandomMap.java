package net.warvale.core.game.start;

import net.warvale.core.maps.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ron on 20/6/2017.
 */
public class VoteRandomMap {
    public List<GameMap> getRandomMaps(Integer mapAmount) {

        /*
        This gets a random amount of maps, from the argument and returns a List with all of the chosen maps.
         */

        Random arandom = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (Integer i = 0; i < mapAmount; i++) {
            numbers.add(arandom.nextInt(GameMap.getMaps().size()));
        }
        List<GameMap> gamemaps = new ArrayList<>();
        for (Integer o : numbers) {
            gamemaps.add(GameMap.getMaps().get(o));
        }
        // Gamemaps contains all of the maps that were randomly selected at this point.
        return gamemaps;
    }
}
