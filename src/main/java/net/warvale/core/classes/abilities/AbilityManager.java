package net.warvale.core.classes.abilities;

import net.warvale.core.classes.Class;
import net.warvale.core.classes.ClassManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

/**
 * Created by Ron on 22/5/2017.
 */
public class AbilityManager implements Listener {

    ArrayList<Player> cooldown = new ArrayList<>();

    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == RIGHT_CLICK_AIR || e.getAction() == RIGHT_CLICK_BLOCK)) return;
        if (!(e.getItem().equals(new ItemStack(Material.FIREWORK_CHARGE)))) return;
        if (cooldown.contains(p)) return;

        Class classCheck = ClassManager.getClassForPlayer(p.getName());

        e.setCancelled(true);

        switch (classCheck.getName()){
            case "Soldier":
                if(e.getItem().equals(new ItemStack(Material.FIREWORK_CHARGE))){
                    this.Soldier(p);
                }
                break;
        }

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                cooldown.remove(p);
                p.sendMessage("You are no longer cooldown");
            }
        }, 200);

    }

    private void Soldier (Player p) {
        Vector vector = p.getLocation().getDirection();
        vector.setY(0.4);
        p.setVelocity(vector);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
    }


}
