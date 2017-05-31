package net.warvale.core.game.start;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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
    * Bosses class - spawns 'bosses' when launched (Launched from Initialization class)
    */

    int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    private String world = "World";

    public void initBoss() {
        int chance = randomWithRange(1, 5);
        Location bossLocation = new Location(Bukkit.getWorld(world), 0, 50, 0 );
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
                break;
            case 5:
                break;

        }
    }

    private void BossWizard(Location bossLocation) {



        Skeleton Wizard = (Skeleton) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.SKELETON);
        Wizard.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 100));
        Wizard.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eWizard"));
        Wizard.setCustomNameVisible(true);
        Wizard.setCanPickupItems(true);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        Wizard.getEquipment().setChestplate(chestplate);
        Wizard.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
    }

    private void BossFarborf(Location bossLocation) {
        /* Thanks MatrixTunnel for the name! - http://i.imgur.com/4qSg1Hf.png */
        Silverfish Farborf = (Silverfish) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.SILVERFISH);
        Farborf.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eFarborf"));
        Farborf.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(15);
        Farborf.setHealth(15);
        Farborf.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1000);
        Farborf.setGlowing(true);
    }

    /* Thanks CommandFox for all of the names below. */
    private void BossGrometator(Location bossLocation) {
        IronGolem Grometator = (IronGolem) bossLocation.getWorld().spawnEntity(bossLocation, EntityType.IRON_GOLEM);
        /* Need to continue work on this thing. */

    }
    private void BossCruitionator(Location bossLocation) {

    }
    private void BossHertopal(Location bossLocation) {

    }



}
