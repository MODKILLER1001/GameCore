package warvale.core.plugin.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import warvale.core.plugin.Main;
import warvale.core.plugin.utils.NumberUtils;

/**
 * Created by Draem on 5/16/2017.
 */
public enum BroadcastType {
    TIP("&b&l[!]", new String[] {"tip 1", "tip 2", "tip 3", "tip 4", "tip 5"}),
    ADVERTISEMENT("&c&l[!]", new String[] {"advertise 1", "advertise 2"});

    private String prefix;
    private String[] messages;

    BroadcastType(String prefix, String[] messages) {
        this.prefix = prefix;
        this.messages = messages;
    }

    public void broadcast() {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&f " + this.messages[NumberUtils.random(this.messages.length - 1, 0)]));
    }

    public void autoBroadcast(long delay, long period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                broadcast();
            }

        }.runTaskTimerAsynchronously(Main.get(), delay, period);
    }
}
