package net.warvale.core.tasks;

import net.warvale.core.map.GameMap;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private int countdown = 10;
    private GameMap map;

    public StartTask(GameMap map) {
        this.countdown = 10;
        this.map = map;
    }

    @Override
    public void run() {
        if (this.countdown > 0) {
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + this.countdown + " seconds.");
            this.countdown--;
        } else {
            cancel();
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "The game has begun on " + ChatColor.RED + this.map.getName());
        }

    }

}
