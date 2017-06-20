package net.warvale.core.game.logic.StageSystem;

import net.warvale.core.game.Game;
import net.warvale.core.map.LocationType;
import net.warvale.core.map.MapLocations;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Ron on 30/5/2017.
 */
public class Bosses {


    /*
    * Bosses class - spawns 'bosses' when launched
    */

    int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void initBoss() {
        int chance = randomWithRange(1, 5);
        Location bossLocation = MapLocations.getObjectLocation(Game.getInstance().getChosenMap(), "center", LocationType.CENTER);
        switch (chance) {
            case 1:
                this.BossWizard(bossLocation);
                break;
            case 2:
                this.BossFarborf(bossLocation);
                break;
            case 3:
                this.BossGrometator(bossLocation);
                break;
            case 4:
                this.BossCruitionator(bossLocation);
                break;
            case 5:
                this.BossHertopal(bossLocation);
                break;

        }
    }

    private void BossWizard(Location bossLocation) {



        Skeleton Wizard = (Skeleton) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.SKELETON);
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 25);
        Wizard.getEquipment().setItemInOffHand(diamonds);
        Wizard.getEquipment().setItemInOffHandDropChance(100);
        Wizard.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 100));
        Wizard.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eWizard"));
        Wizard.setCustomNameVisible(true);
        Wizard.setCanPickupItems(true);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Wizard.getEquipment().setChestplate(chestplate);
        Wizard.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        Wizard.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 1, false, false, Color.RED));
    }

    private void BossFarborf(Location bossLocation) {
        /* Thanks MatrixTunnel for the name! - http://i.imgur.com/4qSg1Hf.png */
        Silverfish Farborf = (Silverfish) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.SILVERFISH);
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 25);
        Farborf.getEquipment().setItemInOffHand(diamonds);
        Farborf.getEquipment().setItemInOffHandDropChance(100);
        Farborf.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eFarborf"));
        Farborf.setCustomNameVisible(true);
        Farborf.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
        Farborf.setHealth(15);
        Farborf.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1000);
        Farborf.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 1, false, false, Color.RED));
    }

    /* Thanks CommandFox for all of the names below. */
    private void BossGrometator(Location bossLocation) {
        IronGolem Grometator = (IronGolem) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.IRON_GOLEM);
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 25);
        Grometator.getEquipment().setItemInOffHand(diamonds);
        Grometator.getEquipment().setItemInOffHandDropChance(100);
        Grometator.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eGrometator"));
        Grometator.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 1, false, false, Color.RED));

        Grometator.setPlayerCreated(false);
        Grometator.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
    }
    private void BossCruitionator(Location bossLocation) {
        Spider Cruitionator = (Spider) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.SPIDER);
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 25);
        Cruitionator.getEquipment().setItemInOffHand(diamonds);
        Cruitionator.getEquipment().setItemInOffHandDropChance(100);
        Cruitionator.setCustomNameVisible(true);
        Cruitionator.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eCruitionator"));
        Cruitionator.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        Cruitionator.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
        Cruitionator.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 1, false, false, Color.RED));
        Cruitionator.getEquipment().setItemInMainHand(new ItemStack(Material.WOOD_SWORD));
        Cruitionator.getEquipment().setItemInOffHandDropChance(0);
    }
    private void BossHertopal(Location bossLocation) {
        PolarBear Hertopal = (PolarBear) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.POLAR_BEAR);
        ItemStack diamonds = new ItemStack(Material.DIAMOND, 25);
        Hertopal.getEquipment().setItemInOffHand(diamonds);
        Hertopal.getEquipment().setItemInOffHandDropChance(100);
        Hertopal.setCustomNameVisible(true);
        Hertopal.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eHertopal"));
        Hertopal.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(25);
        Hertopal.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        Hertopal.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 1, false, false, Color.RED));

    }



}
