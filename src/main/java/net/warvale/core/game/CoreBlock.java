package net.warvale.core.game;

import net.warvale.core.game.end.GameEnd;
import net.warvale.core.game.start.GameStart;
import net.warvale.core.map.ConquestMap;
import net.warvale.core.map.LocationType;
import net.warvale.core.map.MapLocations;
import org.bukkit.Bukkit;
import net.warvale.core.Main;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.Set;

import static net.warvale.core.game.CoreState.*;

/**
 * Created by CommandFox on 6/4/2017.
 */

public class CoreBlock implements Listener {

    private int redCoresBroken = 0;
    private int blueCoresBroken = 0;
    private int coreHP = 20;
    private CoreState coreState = UNBREAKABLE; // UNBREAKABLE = first 10 minutes; BREAKABLE = after first 10 minutes;

    public void setCoreState(CoreState state) {
        coreState = state;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block b = e.getBlock();

        Set<String> blueteamplayers = Main.getTeams().getBlueTeam().getEntries();
        Set<String> redteamplayers = Main.getTeams().getRedTeam().getEntries();

        if (b.getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.CORE)) || b.getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.CORE))){
            e.setCancelled(true);
        }

        if(coreState == UNBREAKABLE) {

            return;

        } else if (coreState == BREAKABLE){

            if(b.getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.CORE)) && redteamplayers.contains(p)) {
                blueCoresBroken = blueCoresBroken + 1;
                if(blueCoresBroken == coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                }
                p.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.SPAWN));

            }

            if(b.getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.CORE)) && blueteamplayers.contains(p)) {
                redCoresBroken = redCoresBroken + 1;
                if(redCoresBroken == coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                }
                p.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.SPAWN));
            }

        } else if (coreState == SPEED_BREAK){

            if(b.getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.CORE)) && redteamplayers.contains(p)) {
                blueCoresBroken = blueCoresBroken + 2;
                if(blueCoresBroken >= coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                }
                p.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.SPAWN));

            }

            if(b.getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.CORE)) && blueteamplayers.contains(p)) {
                redCoresBroken = redCoresBroken + 2;
                if(redCoresBroken >= coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                }
                p.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.SPAWN));
            }

        } else if (coreState == INSTANT_BREAK){

            if(e.getBlock().getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.CORE)) && redteamplayers.contains(p)) {
                GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                p.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.SPAWN));

            }

            if(e.getBlock().getLocation().equals(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "red", LocationType.CORE)) && blueteamplayers.contains(p)) {
                GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                p.teleport(MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "blue", LocationType.SPAWN));
            }
        }
    }
    public int getBlueCoreHealth(){
        return coreHP - blueCoresBroken;
    }
    public int getRedCoreHealth(){
        return coreHP - redCoresBroken;
    }
}
