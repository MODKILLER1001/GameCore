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
public class Abillity implements Listener {


    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == RIGHT_CLICK_AIR || e.getAction() == RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().equals(new ItemStack(Material.FIREWORK_CHARGE)))) return;

        Class classCheck = ClassManager.getClassForPlayer(p.getName());

        if (!classCheck) {
            return;
        }

        switch (classCheck){
            case "Soldier":
                if(e.getItem().equals(new ItemStack(Material.FIREWORK_CHARGE))){
                    this.Soldier();
                }
                break;
        }

    }

    public void Soldier () {
        // Do soldier stuff
    }

}
