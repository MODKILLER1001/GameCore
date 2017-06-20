package net.warvale.core.game;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CommandFox on 6/17/2017.
 */
public class OldPvP {
    double GAS = 999999.0;
    HashMap<String,Double> damageValues = new HashMap<String,Double>();

    public OldPvP () {
        damageValues.put("diamond_sword", 7.0D);
        damageValues.put("gold_sword", 4.0D);
        damageValues.put("iron_sword", 6.0D);
        damageValues.put("stone_sword", 5.0D);
        damageValues.put("wood_sword", 4.0D);
        damageValues.put("diamond_axe", 6.0D);
        damageValues.put("gold_axe", 3.0D);
        damageValues.put("iron_axe", 5.0D);
        damageValues.put("stone_axe", 4.0D);
        damageValues.put("wood_axe", 3.0D);
    }

    @EventHandler
    public void onSwapHandItems(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().getType().equals(InventoryType.CRAFTING)) {
            return;
        }
        if (e.getSlot() != 40) {
            return;
        }
        if ((e.getClick().equals(ClickType.NUMBER_KEY))) {
            e.setResult(Event.Result.DENY);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if ((!e.getInventory().getType().equals(InventoryType.CRAFTING)) || (!e.getInventorySlots().contains(Integer.valueOf(40)))) {
            return;
        }
        e.setResult(Event.Result.DENY);
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World world = p.getWorld();

        AttributeInstance attribute = p.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        double baseValue = attribute.getBaseValue();

        if (baseValue != GAS) {
            attribute.setBaseValue(GAS);
            p.saveData();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        double baseValue = attribute.getBaseValue();
        if (baseValue != 4.0D) {
            attribute.setBaseValue(4.0D);
            player.saveData();
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();

        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        double baseValue = attribute.getBaseValue();
        if (baseValue != GAS) {
            attribute.setBaseValue(GAS);
            player.saveData();
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        Entity p = e.getEntity();
        Entity d = e.getDamager();
        Double damageIncrement = 1.25;

        ItemStack weapon = ((Player)d).getInventory().getItemInMainHand();

        if(p instanceof Player && d instanceof Player){
            for (Map.Entry entry : damageValues.entrySet()) {
                if(weapon.getType().equals(Material.valueOf((String) entry.getKey()))){
                    if(weapon.getEnchantmentLevel(Enchantment.DAMAGE_ALL) > 0) {
                        e.setDamage(weapon.getEnchantmentLevel(Enchantment.DAMAGE_ALL) * 1.25D + (Double) entry.getValue());
                    } else{
                        e.setDamage((Double) entry.getValue());
                    }
                }
            }
        }
    }
}
