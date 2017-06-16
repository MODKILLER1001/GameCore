package net.warvale.core.utils.mc.config;

import net.warvale.core.utils.world.LocationObject;
import org.bukkit.configuration.ConfigurationSection;

public class LocationUtil {
    public static LocationObject fromConfig(ConfigurationSection section) {
        if (section == null) {
            throw new IllegalArgumentException("Section cannot be null");
        }
        double x = getVar(section, "x");
        double y = getVar(section, "y");
        double z = getVar(section, "z");
        return new LocationObject(x, y, z);
    }

    public static double getVar(ConfigurationSection section, String var) {
        return section.getDouble(var);
    }

}
