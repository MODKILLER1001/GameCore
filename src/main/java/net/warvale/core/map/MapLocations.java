package net.warvale.core.map;

import net.warvale.core.maps.GameMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by AAces on 6/20/20179+
 */
public class MapLocations {
    //lobby
    private static Location lobby = new Location(Bukkit.getWorld("lobby"), 383, 103, 71, 0, 0);
    //Redwood Forest
    private static Location redwoodRedSpawn = new Location(Bukkit.getWorld("Redwood_Forest"), -87, 38, -79);
    private static Location redwoodBlueSpawn = new Location(Bukkit.getWorld("Redwood_Forest"), 109, 38, 101);
    private static Location redwoodRedCore = new Location(Bukkit.getWorld("Redwood_Forest"), -85, 17, -74);
    private static Location redwoodBlueCore = new Location(Bukkit.getWorld("Redwood_Forest"), 107, 17, 96);
    //Volcano Island
    private static Location volcanoRedSpawn = new Location(Bukkit.getWorld("Volcano_Island"), -130, 41, -113);
    private static Location volcanoBlueSpawn = new Location(Bukkit.getWorld("Volcano_Island"), 34, 41, 55);
    private static Location volcanoRedCore = new Location(Bukkit.getWorld("Volcano_Island"), -128, 15, -114);
    private static Location volcanoBlueCore = new Location(Bukkit.getWorld("Volcano_Island"), 32, 15, 56);
    //Pagoda Everglade
    private static Location pagodaRedSpawn = new Location(Bukkit.getWorld("Pagoda_Everglade"),-86, 40, -10);
    private static Location pagodaBlueSpawn = new Location(Bukkit.getWorld("Pagoda_Everglade"), 72, 40, -4);
    private static Location pagodaRedCore = new Location(Bukkit.getWorld("Pagoda_Everglade"), -86, 66, -10);
    private static Location pagodaBlueCore = new Location(Bukkit.getWorld("Pagoda_Everglade"), 72, 66, -4);
    //Extraterrestrial
    private static Location extraRedSpawn = new Location(Bukkit.getWorld("Extraterrestrial"), -98, 62, 18);
    private static Location extraBlueSpawn = new Location(Bukkit.getWorld("Extraterrestrial"), 134, 62, 18);
    private static Location extraRedCore = new Location(Bukkit.getWorld("Extraterrestrial"), -69, 75, 33);
    private static Location extraBlueCore = new Location(Bukkit.getWorld("Extraterrestrial"), 105, 75, 33);
    //Canyon Brook
    private static Location canyonRedSpawn = new Location(Bukkit.getWorld("Canyon_Brook"), 90, 41, 68);
    private static Location canyonBlueSpawn = new Location(Bukkit.getWorld("Canyon_Brook"), -120, 41, -96);
    private static Location canyonRedCore = new Location(Bukkit.getWorld("Canyon_Brook"), 90, 69, 91);
    private static Location canyonBlueCore = new Location(Bukkit.getWorld("Canyon_Brook"), -120, 69, -119);
    //Map centers (for bosses)
    private static Location redwoodCenter = new Location(Bukkit.getWorld("Redwood_Forest"), 11, 24, 11);
    private static Location volcanoCenter = new Location(Bukkit.getWorld("Volcano_Island"), -48, 27, -29);
    private static Location pagodaCenter = new Location(Bukkit.getWorld("Pagoda_Everglade"), -7, 34, -7);
    private static Location extraCenter = new Location(Bukkit.getWorld("Extraterrestrial"), 18, 35, 37);
    private static Location canyonCenter = new Location(Bukkit.getWorld("Canyon_Brook"), -15, 30, -14);


    public static Location getObjectLocation(GameMap map, String team, LocationType type){
        if (map == null){
            return lobby;
        }
        if (map == GameMap.getMap("Redwood_Forest")){
            if (team.equals("red")){
                if (type == LocationType.SPAWN){
                    return redwoodRedSpawn;
                } else if (type == LocationType.CORE){
                    return redwoodRedCore;
                }
            } else if (team.equals("blue")){
                if (type == LocationType.SPAWN){
                    return redwoodBlueSpawn;
                } else if (type == LocationType.CORE){
                    return redwoodBlueCore;
                }
            } else if (team.equals("center")){
                return redwoodCenter;
            }
        } else if (map == GameMap.getMap("Pagoda_Everglade")){
            if (team.equals("red")){
                if (type == LocationType.SPAWN){
                    return pagodaRedSpawn;
                } else if (type == LocationType.CORE){
                    return pagodaRedCore;
                }
            } else if (team.equals("blue")){
                if (type == LocationType.SPAWN){
                    return pagodaBlueSpawn;
                } else if (type == LocationType.CORE){
                    return pagodaBlueCore;
                }
            } else if (team.equals("center")){
                return pagodaCenter;
            }
        } else if (map == GameMap.getMap("Volcano_Island")){
            if (team.equals("red")){
                if (type == LocationType.SPAWN){
                    return volcanoRedSpawn;
                } else if (type == LocationType.CORE){
                    return volcanoRedCore;
                }
            } else if (team.equals("blue")){
                if (type == LocationType.SPAWN){
                    return volcanoBlueSpawn;
                } else if (type == LocationType.CORE){
                    return volcanoBlueCore;
                }
            } else if (team.equals("center")){
                return volcanoCenter;
            }
        } else if (map == GameMap.getMap("Extraterrestrial")){
            if (team.equals("red")){
                if (type == LocationType.SPAWN){
                    return extraRedSpawn;
                } else if (type == LocationType.CORE){
                    return extraRedCore;
                }
            } else if (team.equals("blue")){
                if (type == LocationType.SPAWN){
                    return extraBlueSpawn;
                } else if (type == LocationType.CORE){
                    return extraBlueCore;
                }
            } else if (team.equals("center")){
                return extraCenter;
            }
        } else if (map == GameMap.getMap("Canyon_Brook")){
            if (team.equals("red")){
                if (type == LocationType.SPAWN){
                    return canyonRedSpawn;
                } else if (type == LocationType.CORE){
                    return canyonRedCore;
                }
            } else if (team.equals("blue")){
                if (type == LocationType.SPAWN){
                    return canyonBlueSpawn;
                } else if (type == LocationType.CORE){
                    return canyonBlueCore;
                }
            } else if (team.equals("center")){
                return canyonCenter;
            }
        } else {
            return null;
        }
        return null;
    }
}
