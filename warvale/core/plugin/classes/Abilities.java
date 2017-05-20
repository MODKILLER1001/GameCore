package warvale.core.plugin.classes;

import org.bukkit.entity.Player;

public class Abilities {

    public static void detectClass(Player player) {
        if (ClassManager.getClassForPlayer(player.getName()) != null) {
           Class clazz = ClassManager.getClassForPlayer(player.getName());
           assert clazz != null;
           System.out.println(player.getName() + "'s Class: " + clazz.getName());
        }
    }

}
