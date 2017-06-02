package net.warvale.core;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Draem on 5/30/2017.
 */
public class PermissionManager {

    private static HashMap<UUID, PermissionAttachment> permissions = new HashMap<>();

    public static void addPermission(Player player, String permission) {
        if (permissions.keySet().stream().noneMatch(uuid -> uuid.equals(player.getUniqueId()))) {
            permissions.put(player.getUniqueId(), player.addAttachment(Main.get()));
        }
        permissions.get(player.getUniqueId()).setPermission(permission, true);
    }

    public static void removePermission(Player player, String permission) {
        if (permissions.keySet().stream().noneMatch(uuid -> uuid.equals(player.getUniqueId()))) {
            permissions.put(player.getUniqueId(), player.addAttachment(Main.get()));
        }
        permissions.get(player.getUniqueId()).setPermission(permission, false);
    }

    public static void unsetPermission(Player player, String permission) {
        if (permissions.keySet().stream().noneMatch(uuid -> uuid.equals(player.getUniqueId()))) {
            permissions.put(player.getUniqueId(), player.addAttachment(Main.get()));
        }
        permissions.get(player.getUniqueId()).unsetPermission(permission);
    }

    public static void resetPermissions(Player player) {
        if (permissions.keySet().contains(player.getUniqueId())) {
            permissions.remove(player.getUniqueId());
        }
    }

    public static void resetPermissions(UUID player) {
        if (permissions.keySet().contains(player)) {
            permissions.remove(player);
        }
    }

    public static void wipePermissions() {
        permissions.keySet().forEach(PermissionManager::resetPermissions);
    }

}
