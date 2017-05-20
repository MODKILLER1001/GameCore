package warvale.core.plugin.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import warvale.core.plugin.Main;

public class Abilities {

    public static void detectClass(Player player) {

        if (ClassManager.getClassForPlayer(player.getName()) != null) {
            ClassManager.getClassForPlayer(player.getName()).getName();

            if (ClassManager.getClassForPlayer(player.getName()).getName().equalsIgnoreCase("Soldier")) {
                ItemStack soldier_icon = new ItemStack(Material.FIREWORK_CHARGE);
                System.out.println("Soldier");
            }
        }
    }

}
