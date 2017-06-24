package net.warvale.core.utils;

import net.warvale.core.config.ConfigManager;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class LobbyUtils {

    public static void setSpawn(Player player) {

        Location loc = player.getLocation();

        ConfigManager.getConfig().set("spawn.world", loc.getWorld().getName());
        ConfigManager.getConfig().set("spawn.x", loc.getX());
        ConfigManager.getConfig().set("spawn.y", loc.getY());
        ConfigManager.getConfig().set("spawn.z", loc.getZ());
        ConfigManager.getConfig().set("spawn.yaw", loc.getYaw());
        ConfigManager.getConfig().set("spawn.pitch", loc.getPitch());
        ConfigManager.getInstance().saveConfig();

        player.sendMessage(String.format(MessageManager.getPrefix(PrefixType.MAIN) + "You have set the spawnpoint to W: §a%s §7X: §a%s §7Y: §a%s §7Z: §a%s§7.", player.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ()));

    }

    /**
     * Get the spawnpoint of the lobby.
     *
     * @return The lobby spawnpoint.
     */
    public static Location getSpawn() {

        World world = Bukkit.getWorld(ConfigManager.getConfig().getString("spawn.world", "lobby"));

        if (world == null) {
            world = Bukkit.getWorlds().get(0);
        }

        double x = ConfigManager.getConfig().getDouble("spawn.x", 0);
        double y = ConfigManager.getConfig().getDouble("spawn.y", 51);
        double z = ConfigManager.getConfig().getDouble("spawn.z", 0);
        float yaw = (float) ConfigManager.getConfig().getDouble("spawn.yaw", 0);
        float pitch = (float) ConfigManager.getConfig().getDouble("spawn.pitch", 0);

        Location loc = new Location(world, x, y, z, yaw, pitch);
        return loc;
    }

}
