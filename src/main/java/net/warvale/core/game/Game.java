package net.warvale.core.game;


import net.warvale.core.Main;
import net.warvale.core.game.logic.TeamManager;
import net.warvale.core.game.start.Initialization;
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
        Integer tp = Main.getTeams().getBlueTeam().getSize() + Main.getTeams().getRedTeam().getSize();
        new Initialization(map, new MatchInfo(tp, Main.getTeams().getRedTeam().getSize(), Main.getTeams().getBlueTeam().getSize())).startGame();
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
