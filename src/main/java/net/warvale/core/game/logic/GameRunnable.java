package net.warvale.core.game.logic;

import org.bukkit.scheduler.BukkitRunnable;

public class GameRunnable extends BukkitRunnable {

    private static int seconds = 0;

    @Override
    public void run() {
        ++seconds;

    }

    public static int getSeconds() {
        return seconds;
    }

}
