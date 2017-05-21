package net.warvale.core.tasks;

import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private int countdown = 10;
    private static StartTask instance;

    public static StartTask getInstance() {
        if (instance == null) {
            instance = new StartTask();
        }
        return instance;
    }

    @Override
    public void run() {
        countdown--;

        if (countdown > 0) {
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.DARK_RED + "CTF " + ChatColor.GRAY + "starts in " + countdown + " seconds.");
        } else {
            cancel();
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "The game has begun on <map>!");
        }

    }

}
