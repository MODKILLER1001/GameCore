package net.warvale.core.game;

import net.warvale.core.game.end.GameEnd;
import org.bukkit.Bukkit;
import net.warvale.core.Main;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.World;
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

    private World world = Bukkit.getWorld("World");

    private Location coreBlue = new Location(world, 0, 0, 0);
    private Location coreRed = new Location(world, 10, 10, 10);

    private Location spawnBlue = new Location(world, 1, 1, 1);
    private Location spawnRed = new Location(world, 11, 11, 11);

    private int coreHP = 20;

    public CoreState coreState = UNBREAKABLE; // UNBREAKABLE = first 10 minutes; BREAKABLE = after first 10 minutes;

    public void setCoreState(CoreState state) {
        coreState = state;
        return;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        e.setCancelled(true);

        Player p = e.getPlayer();
        Block b = e.getBlock();

        Set<String> blueteamplayers = Main.getTeams().getBlueTeam().getEntries();
        Set<String> redteamplayers = Main.getTeams().getRedTeam().getEntries();

        if(coreState == UNBREAKABLE) {
            return;

        } else if (coreState == BREAKABLE){

            if(b.getLocation().equals(coreBlue) && redteamplayers.contains(p)) {
                if(blueCoresBroken == coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                }

                blueCoresBroken = blueCoresBroken + 1;
                p.teleport(spawnRed);
            }

            if(b.getLocation().equals(coreRed) && blueteamplayers.contains(p)) {
                if(redCoresBroken == coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                }

                redCoresBroken = redCoresBroken + 1;
                p.teleport(spawnBlue);
            }

        } else if (coreState == SPEED_BREAK){

            if(b.getLocation().equals(coreBlue) && redteamplayers.contains(p)) {
                if(blueCoresBroken >= coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                }

                blueCoresBroken = blueCoresBroken + 2;
                p.teleport(spawnRed);
            }

            if(b.getLocation().equals(coreRed) && blueteamplayers.contains(p)) {
                if(redCoresBroken >= coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                }

                redCoresBroken = redCoresBroken + 2;
                p.teleport(spawnBlue);
            }

        } else if (coreState == INSTANT_BREAK){

            if(e.getBlock().getLocation().equals(coreBlue) && redteamplayers.contains(p)) {
                GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                p.teleport(spawnRed);
            }

            if(e.getBlock().getLocation().equals(coreRed) && blueteamplayers.contains(p)) {
                GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                p.teleport(spawnBlue);
            }
        }
    }
}
