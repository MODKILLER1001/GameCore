package net.warvale.core.game.logic;

import net.warvale.core.hooks.DisguiseHook;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import org.bukkit.Bukkit;
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

        //disguise before teleporting
        if (DisguiseHook.getInstance().isEnabled() && seconds == 0) {

            DisguiseHook.getInstance().getAPI().undisguiseAll();

            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.sendMessage(MessageManager.getPrefix(PrefixType.MAIN) + "You are now undisguised");
            }

        }


    }

    public static int getSeconds() {
        return seconds;
    }

}
