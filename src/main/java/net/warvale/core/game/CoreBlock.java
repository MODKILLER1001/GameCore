package net.warvale.core.game;

import net.warvale.core.game.end.GameEnd;
import net.warvale.core.game.start.GameStart;
import net.warvale.core.map.ConquestMap;
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

    public CoreState coreState = UNBREAKABLE; // UNBREAKABLE = first 10 minutes; BREAKABLE = after first 10 minutes;

    public void setCoreState(CoreState state) {
        coreState = state;
        return;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block b = e.getBlock();

        Set<String> blueteamplayers = Main.getTeams().getBlueTeam().getEntries();
        Set<String> redteamplayers = Main.getTeams().getRedTeam().getEntries();

        if (b.getLocation().equals(new ConquestMap(GameStart.map.getName()).getBlueCore().toLocation(Bukkit.getWorld(GameStart.map.getName()))) || b.getLocation().equals(new ConquestMap(GameStart.map.getName()).getRedCore().toLocation(Bukkit.getWorld(GameStart.map.getName())))){
            e.setCancelled(true);
        }
        //TODO: Range Protect
        if(coreState == UNBREAKABLE) {

            return;

        } else if (coreState == BREAKABLE){

            if(b.getLocation().equals(new ConquestMap(GameStart.map.getName()).getBlueCore().toLocation(Bukkit.getWorld(GameStart.map.getName()))) && redteamplayers.contains(p)) {
                if(blueCoresBroken == coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                }

                blueCoresBroken = blueCoresBroken + 1;
                p.teleport(new ConquestMap(GameStart.map.getName()).getRedSpawn().toLocation(Bukkit.getWorld(GameStart.map.getName())));
            }

            if(b.getLocation().equals(new ConquestMap(GameStart.map.getName()).getRedCore().toLocation(Bukkit.getWorld(GameStart.map.getName()))) && blueteamplayers.contains(p)) {
                if(redCoresBroken == coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                }

                redCoresBroken = redCoresBroken + 1;
                p.teleport(new ConquestMap(GameStart.map.getName()).getBlueSpawn().toLocation(Bukkit.getWorld(GameStart.map.getName())));
            }

        } else if (coreState == SPEED_BREAK){

            if(b.getLocation().equals(new ConquestMap(GameStart.map.getName()).getBlueCore().toLocation(Bukkit.getWorld(GameStart.map.getName()))) && redteamplayers.contains(p)) {
                if(blueCoresBroken >= coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                }

                blueCoresBroken = blueCoresBroken + 2;
                p.teleport(new ConquestMap(GameStart.map.getName()).getRedSpawn().toLocation(Bukkit.getWorld(GameStart.map.getName())));
            }

            if(b.getLocation().equals(new ConquestMap(GameStart.map.getName()).getRedCore().toLocation(Bukkit.getWorld(GameStart.map.getName()))) && blueteamplayers.contains(p)) {
                if(redCoresBroken >= coreHP){
                    GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                }

                redCoresBroken = redCoresBroken + 2;
                p.teleport(new ConquestMap(GameStart.map.getName()).getBlueSpawn().toLocation(Bukkit.getWorld(GameStart.map.getName())));
            }

        } else if (coreState == INSTANT_BREAK){

            if(e.getBlock().getLocation().equals(new ConquestMap(GameStart.map.getName()).getBlueCore().toLocation(Bukkit.getWorld(GameStart.map.getName()))) && redteamplayers.contains(p)) {
                GameEnd.coreBrokenEnd(Main.getTeams().getRedTeam());
                p.teleport(new ConquestMap(GameStart.map.getName()).getRedSpawn().toLocation(Bukkit.getWorld(GameStart.map.getName())));
            }

            if(e.getBlock().getLocation().equals(new ConquestMap(GameStart.map.getName()).getRedCore().toLocation(Bukkit.getWorld(GameStart.map.getName()))) && blueteamplayers.contains(p)) {
                GameEnd.coreBrokenEnd(Main.getTeams().getBlueTeam());
                p.teleport(new ConquestMap(GameStart.map.getName()).getBlueSpawn().toLocation(Bukkit.getWorld(GameStart.map.getName())));
            }
        }
    }
}
