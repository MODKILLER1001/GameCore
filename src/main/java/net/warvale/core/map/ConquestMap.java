package net.warvale.core.map;

import net.warvale.core.utils.mc.config.LocationUtil;
import net.warvale.core.utils.world.LocationObject;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.*;
import net.warvale.core.maps.*;

public class ConquestMap extends GameMap {

    public ConquestMap(String name) {
        super(name);
    }


    @SuppressWarnings("unchecked")
    public Map loadSetting(ConfigurationSection configurationSection) {
        Map map = new HashMap<>();


        LocationObject blueSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("spawns.blue"));
        map.put("blueSpawn", blueSpawn);

        LocationObject redSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("spawns.red"));
        map.put("redSpawn", redSpawn);

        return map;
    }

    @SuppressWarnings("unchecked")
    public LocationObject getBlueSpawn() {
        return (LocationObject) getSettings().get("blueSpawn");
    }

    @SuppressWarnings("unchecked")
    public LocationObject getRedSpawn() {
        return (LocationObject) getSettings().get("redSpawn");
    }

    @SuppressWarnings("unchecked")
    public LocationObject getBlueCore() {
        return (LocationObject) getSettings().get("blueCore");
    }

    @SuppressWarnings("unchecked")
    public LocationObject getRedCore() {
        return (LocationObject) getSettings().get("redCore");
    }

}
