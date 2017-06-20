package net.warvale.core.tasks;

import net.warvale.core.Main;
import net.warvale.core.classes.ClassManager;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.logic.GameRunnable;
import net.warvale.core.game.logic.StageSystem.Stages;
import net.warvale.core.game.scoreboards.GameScoreboard;
import net.warvale.core.game.scoreboards.LobbyScoreboard;
import net.warvale.core.game.start.GameStart;
import net.warvale.core.map.ConquestMap;
import net.warvale.core.map.LocationType;
import net.warvale.core.map.MapLocations;
import net.warvale.core.maps.GameMap;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class StartGameTask extends BukkitRunnable {

    @Override
    public void run() {

        try {
            new Stages().initStages();
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "The game has begun on " + ChatColor.RED +
                    Game.getInstance().getChosenMap().getName() + ChatColor.GRAY + "!");
            BarManager.getAnnounceBar().setVisible(false);
            for (Player player : GameStart.inGame) {
                player.removePotionEffect(PotionEffectType.SLOW);
                player.removePotionEffect(PotionEffectType.JUMP);
            }

            for (Player player : GameStart.teamBlue) {
                //teleport player
                player.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.SPAWN));
                //give class
                //player.getInventory().addItem(ClassManager.getClassForPlayer(player.getName()).getItem());
            }
            for (Player player : GameStart.teamRed) {
                //teleport player
                player.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.SPAWN));
                //give class
                //player.getInventory().addItem(ClassManager.getClassForPlayer(player.getName()).getItem());
            }

        } catch (Exception ex) {
            Main.get().getLogger().log(Level.SEVERE, "Could not teleport players to chosen map", ex);
        }

        //configure scoreboards
        for (Player player : Bukkit.getOnlinePlayers()) {
            LobbyScoreboard.getInstance().removeScoreboard(player);
            GameScoreboard.getInstance().addScoreboard(player);
        }

        Game.getInstance().setState(State.INGAME);
        new GameRunnable(this).runTaskTimer(Main.get(), 20, 20);
    }


}
