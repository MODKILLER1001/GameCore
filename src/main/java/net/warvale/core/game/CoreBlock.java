package net.warvale.core.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import net.warvale.core.Main;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.Set;

/**
 * Created by CommandFox on 6/4/2017.
 */

public class CoreBlock implements Listener {

    private int redBlocksBroken = 0;
    private int blueBlocksBroken = 0;

    private World world = Bukkit.getWorld("World");

    private Location coreBlue = new Location(world, 0, 0, 0);
    private Location coreRed = new Location(world, 10, 10, 10);

    private Location spawnBlue = new Location(world, 1, 1, 1);
    private Location spawnRed = new Location(world, 11, 11, 11);

    private int coreHP = 10;

    public int coreState = 0; // 0 = first 10 minutes; 1 = after first 10 minutes;

    public void setCoreState(int state) {
        coreState = state;
        return;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if(coreState == 0) {
            e.setCancelled(true);
            return;
        }

        Player p = e.getPlayer();
        Block b = e.getBlock();

        Set<String> blueteamplayers = Main.getTeams().getBlueTeam().getEntries();
        Set<String> redteamplayers = Main.getTeams().getRedTeam().getEntries();

        if(e.getBlock().getLocation().equals(coreBlue) && redteamplayers.contains(p)) {
            if(redBlocksBroken == coreHP){
                /* Red Wins */
            }

            redBlocksBroken += 1;
            p.teleport(spawnRed);
        }

        if(e.getBlock().getLocation().equals(coreRed) && blueteamplayers.contains(p)) {
            if(blueBlocksBroken == coreHP){
                /* Blue Wins */
            }

            blueBlocksBroken += 1;
            p.teleport(spawnBlue);
        }

    }
}
