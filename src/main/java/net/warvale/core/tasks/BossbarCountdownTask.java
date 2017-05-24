package net.warvale.core.tasks;


import net.warvale.core.utils.dates.DateUtils;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.scheduler.BukkitRunnable;

public class BossbarCountdownTask extends BukkitRunnable {

    private int countdown = 5 * 60;

    public BossbarCountdownTask() {
        this.countdown = 5 * 60;
    }

    @Override
    public void run(){
        BarManager.getAnnounceBar().setVisible(true);
        if (this.countdown > 0) {
            BarManager.getAnnounceBar().setTitle(ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + DateUtils.secondsToString(countdown));
            BarManager.getAnnounceBar().setColor(BarColor.BLUE);

            BarManager.getAnnounceBar().setProgress((float)countdown/(float)(60 * 5));
            countdown--;
        } else {
            BarManager.getAnnounceBar().setVisible(false);
            cancel();
        }
    }

}
