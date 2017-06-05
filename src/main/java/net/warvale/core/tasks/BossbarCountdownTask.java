package net.warvale.core.tasks;


import net.warvale.core.Main;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.logic.StageSystem.Stages;
import net.warvale.core.game.start.GameStart;
import net.warvale.core.game.start.Initialization;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.utils.dates.DateUtils;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.scheduler.BukkitRunnable;

import static net.warvale.core.game.start.GameStart.info;

public class BossbarCountdownTask extends BukkitRunnable {

    private static int countdown = 1 * 60 + 1;
    public static boolean countdownActive = false;

    public BossbarCountdownTask() {
        countdown = 1 * 60 + 1;
    }

    @Override
    public void run(){
        countdownActive = true;
        countdown = countdown - 1;
        Game.getInstance().setState(State.COUNTDOWN);
        BarManager.getAnnounceBar().setVisible(true);
        if (countdown == 15){
            GameStart.voteTally();
        }
        if (countdown == 10){
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + ChatColor.RED + countdown + ChatColor.GRAY + (countdown == 1 ? " second." : " seconds."));
            new Initialization(GameStart.map, info).startGame();
        }
        if (countdown <= 9 && countdown >= 1){
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + ChatColor.RED + countdown + ChatColor.GRAY + (countdown == 1 ? " second." : " seconds."));
            BarManager.broadcastSound(Sound.BLOCK_NOTE_PLING);
        }
        if (countdown <= 0){
            countdownActive = false;
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "The game has begun on " + ChatColor.RED + GameStart.map.getName() + ChatColor.GRAY + "!");
            //Insert method to start the game.
            BarManager.getAnnounceBar().setVisible(false);
            new Stages().initStages();
            this.cancel();
            return;
        }
        BarManager.getAnnounceBar().setTitle(ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + DateUtils.secondsToString(countdown));
        BarManager.getAnnounceBar().setProgress((float)countdown/(float)(60 * 5));
        BarManager.getAnnounceBar().setColor(BarColor.BLUE);
    }

    public static int getCountdown() {
        return countdown;
    }
}
