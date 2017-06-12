package net.warvale.core.game.logic.StageSystem;

import net.warvale.core.Main;
import net.warvale.core.game.CoreBlock;
import net.warvale.core.game.CoreState;
import net.warvale.core.game.end.GameEnd;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ron on 4/6/2017.
 */
public class Stages {
    /* CurrentStage variable + get method */
    private Stage CurrentStage;
    public Stage getCurrentStage() {return CurrentStage;}
    private Plugin pl = Main.get();

    public void initStages() {
        CurrentStage = Stage.INVINCIBLE_CORE;
        // game_start
        new CoreBlock().setCoreState(CoreState.UNBREAKABLE);

        /* 10 minutes in */
        new BukkitRunnable() {

            @Override
            public void run() {
           new Bosses().initBoss();
           new CoreBlock().setCoreState(CoreState.BREAKABLE);
           CurrentStage = Stage.BOSS_DIAMOND;
            }

        }.runTaskLater(this.pl, 12000);


        /* 30 minutes in */
        new BukkitRunnable() {

            @Override
            public void run() {
            CurrentStage = Stage.CORE_BREAK_FAST;
            new CoreBlock().setCoreState(CoreState.SPEED_BREAK);
            }

        }.runTaskLater(this.pl, 36000);




        /* 40 minutes in */
        new BukkitRunnable() {

            @Override
            public void run() {
            CurrentStage = Stage.DEATHMATCH;
            new CoreBlock().setCoreState(CoreState.INSTANT_BREAK);
            }

        }.runTaskLater(this.pl, 48000);


        /* 1 hour in */
        new BukkitRunnable() {

            @Override
            public void run() {
                GameEnd.tieEnd();
            }

        }.runTaskLater(this.pl, 72000);

    }


}
