package net.warvale.core.tasks;

import net.warvale.core.game.Game;
import net.warvale.staffcore.StaffCore;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyTask extends BukkitRunnable {

    @Override
    public void run() {

        if (Bukkit.getServer().getOnlinePlayers().size() >= Game.getInstance().getMinPlayers()) {
            //start the countdown
            new BossbarCountdownTask().runTaskTimer(StaffCore.get(), 20, 20);
        }

    }

}
