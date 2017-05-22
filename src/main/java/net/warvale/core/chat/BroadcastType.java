package net.warvale.core.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import net.warvale.core.Main;
import net.warvale.core.utils.NumberUtils;

/**
 * Created by Draem on 5/16/2017.
 */
public enum BroadcastType {
    TIP("&b&l[!]",
            new String[] { "There is a lapis mine located towards the edge of each team's base!",
                    "You can change disable join and leave messages in the &bPreferences.",
                    "Traps are a very effective way to kill enemies secretively!",
                    "On each map, there is a monster spawner hidden on each team's side!",
                    "Your core is invincible for the first 10 minutes of the game!" }), ADVERTISEMENT(
                            "&2&l[!]",
                            new String[] {
                                    "Follow @warvalenetwork on Twitter for giveaways and news at" + ChatColor.AQUA
                                            + " https://twitter.com/warvalenetwork ",
                                    "Support us and help grow the community by purchasing a rank at" + ChatColor.AQUA
                                            + " https://store.warvale.net " });

    private String prefix;
    private String[] messages;

    BroadcastType(String prefix, String[] messages) {
        this.prefix = prefix;
        this.messages = messages;
    }

    public void broadcast() {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                this.prefix + "&f " + this.messages[NumberUtils.random(this.messages.length - 1, 0)]));
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
