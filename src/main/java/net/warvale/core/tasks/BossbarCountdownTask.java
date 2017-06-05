package net.warvale.core.tasks;


import net.warvale.core.Main;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.start.GameStart;
import net.warvale.core.game.start.Initialization;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.utils.dates.DateUtils;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.scheduler.BukkitRunnable;

import static net.warvale.core.game.start.GameStart.info;

public class BossbarCountdownTask extends BukkitRunnable {

    private static int countdown = 1 * 60 + 1;

    public BossbarCountdownTask() {
        countdown = 1 * 60 + 1;
    }

    @Override
    public void run(){
        countdown = countdown - 1;
        Game.getInstance().setState(State.COUNTDOWN);
        BarManager.getAnnounceBar().setVisible(true);
        if (countdown >= 1) {
            BarManager.getAnnounceBar().setTitle(ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + DateUtils.secondsToString(countdown));
            BarManager.getAnnounceBar().setColor(BarColor.BLUE);
        } else {
            BarManager.getAnnounceBar().setVisible(false);
            MessageManager.broadcast(PrefixType.MAIN, "bossbarcountdowntask start game");
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "The game has begun on " + ChatColor.RED + GameStart.map.getName());
            //Insert method to start the game.
            cancel();
            return;
        }
        if (countdown == 15){
            GameStart.voteTally();
            MessageManager.broadcast(PrefixType.MAIN, "bossbarcountdowntask votetally");
        }
        if (countdown == 10){
            new Initialization(GameStart.map, info).startGame();
        }
        if (countdown <= 10){
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + countdown + " seconds.");
        }
        BarManager.getAnnounceBar().setProgress((float)countdown/(float)(60 * 5));
    }

    public static int getCountdown() {
        return countdown;
    }
}
