package warvale.core.plugin.classes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Draem on 5/12/2017.
 */
public class ClassManager {

    public static HashMap<String, Class> classes = new HashMap<>();

    public static void initClass(Class clazz) {
        System.out.println("Initializing class: " + clazz.getName());
        classes.put(clazz.getName(), clazz);
        System.out.println(clazz.getName() + " initialized!");
    }

    public static boolean hasClass(Player player) {
        for (Map.Entry<String, Class> entry : classes.entrySet()) {
            if (entry.getValue().getMembers().contains(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isClass(String name) {
        for (Map.Entry<String, Class> entry : classes.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static Class getClassByName(String name) {
        for (Map.Entry<String, Class> entry : classes.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static Class getClassForPlayer(String name) {
        if (hasClass(Bukkit.getPlayer(name))) {
            for (Map.Entry<String, Class> entry : classes.entrySet()) {
                if (entry.getValue().getMembers().contains(Bukkit.getPlayer(name))) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

}
