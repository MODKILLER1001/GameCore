package net.warvale.core.start;

import net.warvale.core.map.GameMap;
import net.warvale.core.utils.NumberUtils;

/**
 * Created by Draem on 5/20/2017.
 */
public class Game {

    private static boolean running = false;

    public static void start(GameMap last, GameMap next) {
        GameMap map = next == null ? getMap(last) : next;
        running = true;

    }

    public static void end(GameMap next) {
        running = false;
    }

    public static void cycle(GameMap map) {

    }

    private static GameMap getMap(GameMap map) {
        GameMap[] maps = (GameMap[]) GameMap.getMaps().values().toArray();
        Integer num = NumberUtils.random(maps.length - 1, 0);
        if (map != null) {
            return maps[num] == map ? maps[num + 1] : maps[num];
        } else {
            return maps[num];
        }
    }

    public static boolean isRunning() {
        return running;
    }

}
