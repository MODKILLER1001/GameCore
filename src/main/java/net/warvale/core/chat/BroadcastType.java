package net.warvale.core.chat;

import net.warvale.core.spec.Preferences;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.warvale.core.Main;
import net.warvale.core.utils.NumberUtils;

import java.util.ArrayList;

/**
 * Created by Draem on 5/16/2017.
 */
public enum BroadcastType {
    TIP("&b&l[!]",
            new String[] { "There is a lapis mine located towards the edge of each team's base!",
                    "You can change disable join and leave messages in the &bPreferences.",
                    "Traps are a very effective way to kill enemies secretively!",
                    "On each map, there is a monster spawner hidden on each team's side!",
                    "Your core is invincible for the first 10 minutes of the game!",
                    "You can change what color you see your name in chat as via " + ChatColor.AQUA + " /chatnamecolor" + ChatColor.WHITE + "!"}), ADVERTISEMENT(
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

    public static void broadcastTip() {
        for (Player target : Bukkit.getServer().getOnlinePlayers()){
            if (!Preferences.noTipMessages.contains(target.getName())){
                target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b&l[!]" + "&f " + TIP.messages[NumberUtils.random(TIP.messages.length - 1, 0)]));
            }
        }


    }

    public static void autoBroadcastTip(long delay, long period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                broadcastTip();
            }

        }.runTaskTimerAsynchronously(Main.get(), delay, period);
    }
    public static void broadcastAdvertisement() {
        for (Player target : Bukkit.getServer().getOnlinePlayers()){
            if (!Preferences.noAdvertisementMessages.contains(target.getName())){
                target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&2&l[!]" + "&f " + ADVERTISEMENT.messages[NumberUtils.random(ADVERTISEMENT.messages.length - 1, 0)]));
            }
        }


    }

    public static void autoBroadcastAdvertisement(long delay, long period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                broadcastAdvertisement();
            }

        }.runTaskTimerAsynchronously(Main.get(), delay, period);
    }
}
