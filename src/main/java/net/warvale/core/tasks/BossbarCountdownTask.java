package net.warvale.core.tasks;


import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.utils.dates.DateUtils;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.scheduler.BukkitRunnable;

public class BossbarCountdownTask extends BukkitRunnable {

    private static int countdown = 5 * 60;

    public BossbarCountdownTask() {
        countdown = 5 * 60;
    }

    @Override
    public void run(){
        Game.getInstance().setState(State.COUNTDOWN);
        BarManager.getAnnounceBar().setVisible(true);
        if (countdown > 0) {
            BarManager.getAnnounceBar().setTitle(ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + DateUtils.secondsToString(countdown));
            BarManager.getAnnounceBar().setColor(BarColor.BLUE);

            BarManager.getAnnounceBar().setProgress((float)countdown/(float)(60 * 5));
            countdown--;
        } else {
            BarManager.getAnnounceBar().setVisible(false);
            cancel();
        }
    }

    public static int getCountdown() {
        return countdown;
    }

}
