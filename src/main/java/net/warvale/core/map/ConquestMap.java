package net.warvale.core.map;

import net.warvale.core.utils.mc.config.LocationUtil;
import net.warvale.core.utils.world.LocationObject;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.*;
import net.warvale.core.maps.*;

public class ConquestMap extends GameMap {

    public ConquestMap(String name) {
        super(name);
    }

    private double redSpawnMinX,redSpawnMinY,redSpawnMinZ,redCoreMinX,redCoreMinY,redCoreMinZ,blueSpawnMinX,blueSpawnMinY,blueSpawnMinZ,blueCoreMinX,blueCoreMinY,blueCoreMinZ,redSpawnMaxX,redSpawnMaxY,redSpawnMaxZ,redCoreMaxX,redCoreMaxY,redCoreMaxZ,blueSpawnMaxX,blueSpawnMaxY,blueSpawnMaxZ,blueCoreMaxX,blueCoreMaxY,blueCoreMaxZ;


    @SuppressWarnings("unchecked")
    public Map loadSetting(ConfigurationSection configurationSection) {
        Map map = new HashMap<>();


        LocationObject blueSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("spawns.blue"));
        map.put("blueSpawn", blueSpawn);

        LocationObject redSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("spawns.red"));
        map.put("redSpawn", redSpawn);


        LocationObject blueCore = LocationUtil.fromConfig(configurationSection.getConfigurationSection("cores.blue"));
        map.put("blueCore", blueCore);

        LocationObject redCore = LocationUtil.fromConfig(configurationSection.getConfigurationSection("cores.red"));
        map.put("redCore", redCore);

        redSpawnMinX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.red.min"), "x");
        redSpawnMinY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.red.min"), "y");
        redSpawnMinZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.red.min"), "z");

        redCoreMinX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.red.min"), "x");
        redCoreMinY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.red.min"), "y");
        redCoreMinZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.red.min"), "z");

        blueSpawnMinX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.blue.min"), "x");
        blueSpawnMinY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.blue.min"), "y");
        blueSpawnMinZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.blue.min"), "z");

        blueCoreMinX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.blue.min"), "x");
        blueCoreMinY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.blue.min"), "y");
        blueCoreMinZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.blue.min"), "z");



        redSpawnMaxX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.red.max"), "x");
        redSpawnMaxY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.red.max"), "y");
        redSpawnMaxZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.red.max"), "z");

        redCoreMaxX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.red.max"), "x");
        redCoreMaxY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.red.max"), "y");
        redCoreMaxZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.red.max"), "z");

        blueSpawnMaxX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.blue.max"), "x");
        blueSpawnMaxY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.blue.max"), "y");
        blueSpawnMaxZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.spawns.blue.max"), "z");

        blueCoreMaxX = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.blue.max"), "x");
        blueCoreMaxY = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.blue.max"), "y");
        blueCoreMaxZ = LocationUtil.getVar(configurationSection.getConfigurationSection("regions.cores.blue.max"), "z");

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



    //regions
    @SuppressWarnings("unchecked")
    private double getBlueSpawnMinX() {
        return blueSpawnMinX;
    }
    @SuppressWarnings("unchecked")
    private double getBlueSpawnMinY() {
        return blueSpawnMinY;
    }
    @SuppressWarnings("unchecked")
    private double getBlueSpawnMinZ() {
        return blueSpawnMinZ;
    }
    @SuppressWarnings("unchecked")
    private double getRedSpawnMinX() {
        return redSpawnMinX;
    }
    @SuppressWarnings("unchecked")
    private double getRedSpawnMinY() {
        return redSpawnMinY;
    }
    @SuppressWarnings("unchecked")
    private double getRedSpawnMinZ() {
        return redSpawnMinZ;
    }
    @SuppressWarnings("unchecked")
    private double getBlueSpawnMaxX() {
        return blueSpawnMaxX;
    }
    @SuppressWarnings("unchecked")
    private double getBlueSpawnMaxY() {
        return blueSpawnMaxY;
    }
    @SuppressWarnings("unchecked")
    private double getBlueSpawnMaxZ() {
        return blueSpawnMaxZ;
    }
    @SuppressWarnings("unchecked")
    private double getRedSpawnMaxX() {
        return redSpawnMaxX;
    }
    @SuppressWarnings("unchecked")
    private double getRedSpawnMaxY() {
        return redSpawnMaxY;
    }
    @SuppressWarnings("unchecked")
    private double getRedSpawnMaxZ() {
        return redSpawnMaxZ;
    }
    @SuppressWarnings("unchecked")
    private double getBlueCoreMinX() {
        return blueCoreMinX;
    }
    @SuppressWarnings("unchecked")
    private double getBlueCoreMinY() {
        return blueCoreMinY;
    }
    @SuppressWarnings("unchecked")
    private double getBlueCoreMinZ() {
        return blueCoreMinZ;
    }
    @SuppressWarnings("unchecked")
    private double getRedCoreMinX() {
        return redCoreMinX;
    }
    @SuppressWarnings("unchecked")
    private double getRedCoreMinY() {
        return redCoreMinY;
    }
    @SuppressWarnings("unchecked")
    private double getRedCoreMinZ() {
        return redCoreMinZ;
    }
    @SuppressWarnings("unchecked")
    private double getBlueCoreMaxX() {
        return blueCoreMaxX;
    }
    @SuppressWarnings("unchecked")
    private double getBlueCoreMaxY() {
        return blueCoreMaxY;
    }
    @SuppressWarnings("unchecked")
    private double getBlueCoreMaxZ() {
        return blueCoreMaxZ;
    }
    @SuppressWarnings("unchecked")
    private double getRedCoreMaxX() {
        return redCoreMaxX;
    }
    @SuppressWarnings("unchecked")
    private double getRedCoreMaxY() {
        return redCoreMaxY;
    }
    @SuppressWarnings("unchecked")
    private double getRedCoreMaxZ() {
        return redCoreMaxZ;
    }


    public boolean crossCheckCoords(double x, double y, double z, String team){

        if ((x >= getRedSpawnMinX() && x <= getRedSpawnMaxX()) && (y >= getRedSpawnMinY() && y <= getRedSpawnMaxY()) && (z >= getRedSpawnMinZ() && z <= getRedSpawnMaxZ())){
            return true;
        }

        if (team.equalsIgnoreCase("blue")) {
            if ((x >= getRedCoreMinX() && x <= getRedCoreMaxX()) && (y >= getRedCoreMinY() && y <= getRedCoreMaxY()) && (z >= getRedCoreMinZ() && z <= getRedCoreMaxZ())) {
                return true;
            }
        }

        if ((x >= getBlueSpawnMinX() && x <= getBlueSpawnMaxX()) && (y >= getBlueSpawnMinY() && y <= getBlueSpawnMaxY()) && (z >= getBlueSpawnMinZ() && z <= getBlueSpawnMaxZ())){
            return true;
        }

        if (team.equalsIgnoreCase("red")) {
            if ((x >= getBlueCoreMinX() && x <= getBlueCoreMaxX()) && (y >= getBlueCoreMinY() && y <= getBlueCoreMaxY()) && (z >= getBlueCoreMinZ() && z <= getBlueCoreMaxZ())) {
                return true;
            }
        }

        return false;
    }

}
