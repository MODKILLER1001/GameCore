package net.warvale.core.classes.abilities;

import net.warvale.core.classes.Class;
import net.warvale.core.classes.ClassManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

/**
 * Created by Ron on 22/5/2017.
 */
public class SoliderAbillity implements Listener {


    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == RIGHT_CLICK_AIR || e.getAction() == RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().equals(new ItemStack(Material.FIREWORK_CHARGE)))) return;

        Class PlayerClass = ClassManager.getClassForPlayer(p.getName());
        // CommandFox, could you add the class check to make sure they are using solider class.


        Vector velocity = p.getLocation().getDirection();
        // I. have. no. idea. what. should. I. do. to. make. this. work. Send help.

    }
}
