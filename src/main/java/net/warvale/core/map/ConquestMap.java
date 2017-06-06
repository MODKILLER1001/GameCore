package net.warvale.core.map;

import net.warvale.core.utils.mc.config.LocationUtil;
import net.warvale.core.utils.world.LocationObject;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.*;
import net.warvale.core.maps.*;

public class ConquestMap extends net.warvale.core.maps.GameMap {

    public ConquestMap(String name, MapData data, File yml, File zip) {
        super(name, data, yml, zip);
    }


    @SuppressWarnings("unchecked")
    public Map loadSetting(ConfigurationSection configurationSection) {
        Map map = new HashMap<>();

        Set<Cord> blueTeamCords = new HashSet<>();
        Set<Cord> redTeamCords = new HashSet<>();

        map.put("blueTeamCords", blueTeamCords);
        map.put("redTeamCords", redTeamCords);

        LocationObject blueSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("blueSpawn"));
        map.put("blueSpawn", blueSpawn);

        LocationObject redSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("redSpawn"));
        map.put("redSpawn", redSpawn);

        return map;
    }

    @SuppressWarnings("unchecked")
    public Set<Cord> getBlueTeamCords() {
        return (Set<Cord>) getSettings().get("blueTeamCords");
    }

    @SuppressWarnings("unchecked")
    public Set<Cord> getRedTeamCords() {
        return (Set<Cord>) getSettings().get("redTeamCords");
    }

    @SuppressWarnings("unchecked")
    public LocationObject getBlueSpawn() {
        return (LocationObject) getSettings().get("blueSpawn");
    }

    @SuppressWarnings("unchecked")
    public LocationObject getRedSpawn() {
        return (LocationObject) getSettings().get("redSpawn");
    }

}
