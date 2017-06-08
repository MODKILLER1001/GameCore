package net.warvale.core.game.logic.StageSystem;

import net.warvale.core.Main;
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
        /* game_start
        <Start CommandFox's invincible cores> */

        /* 10 minutes in */
        new BukkitRunnable() {

            @Override
            public void run() {
           new Bosses().initBoss();
            /* <stop  CommandFox's invincible cores */
            CurrentStage = Stage.BOSS_DIAMOND;
            }

        }.runTaskLater(this.pl, 12000);


        /* 30 minutes in */
        new BukkitRunnable() {

            @Override
            public void run() {
            CurrentStage = Stage.CORE_BREAK_FAST;
            /* <insert CommandFox's core break speed multiplier and set to 2x */
            }

        }.runTaskLater(this.pl, 12000);




        /* 40 minutes in */
        new BukkitRunnable() {

            @Override
            public void run() {
            CurrentStage = Stage.DEATHMATCH;
            /* <insert CommandFox's core break speed multiplier and set to 100x */
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
