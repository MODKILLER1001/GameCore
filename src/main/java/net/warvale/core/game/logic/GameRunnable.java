package net.warvale.core.game.logic;

import net.warvale.core.hooks.DisguiseHook;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRunnable extends BukkitRunnable {

    private static int seconds = 0;

    public GameRunnable(BukkitRunnable startTask) {
        startTask.cancel();
    }

    @Override
    public void run() {
        ++seconds;

    }

    public static int getSeconds() {
        return seconds;
    }

}
