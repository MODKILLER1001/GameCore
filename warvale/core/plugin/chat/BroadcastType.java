package warvale.core.plugin.chat;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import warvale.core.plugin.Main;
import warvale.core.plugin.utils.NumberUtils;

/**
 * Created by Draem on 5/16/2017.
 */
public enum BroadcastType {
    TIP("&b&l[!]", new String[] {"tip 1", "tip 2", "tip 3", "tip 4", "tip 5"});

    private String prefix;
    private String[] messages;

    BroadcastType(String prefix, String[] messages) {
        this.prefix = prefix;
        this.messages = messages;
    }

    public void broadcast() {
        Bukkit.broadcastMessage(this.prefix + " " + this.messages[NumberUtils.random(0, this.messages.length - 1)]);
    }

    public void autoBroadcast(long delay, long period) {
        new BukkitRunnable() {
            @Override
            public void run() {

            }

        }.runTaskTimer(Bukkit.getPluginManager().getPlugin(Main.get().getDescription().getName()), delay, period);
    }
}
