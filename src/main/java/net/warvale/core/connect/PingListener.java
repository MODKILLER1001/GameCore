package net.warvale.core.connect;

import net.md_5.bungee.api.ChatColor;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.tasks.BossbarCountdownTask;
import net.warvale.core.utils.dates.DateUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingListener implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {

        e.setMaxPlayers(Game.getInstance().getMaxPlayer());

        if (Game.getInstance().isState(State.LOBBY))  {

            int minPlayers = Game.getInstance().getMinPlayers() - Bukkit.getOnlinePlayers().size();

            e.setMotd(MessageManager.getPrefix(PrefixType.MAIN) + ChatColor.RED +
                    String.valueOf(minPlayers) + ChatColor.DARK_GREEN +
                    " more players needed to start the game, join now!");

        }

        if (Game.getInstance().isState(State.COUNTDOWN)) {
            e.setMotd(MessageManager.getPrefix(PrefixType.MAIN) + ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + DateUtils.secondsToString(BossbarCountdownTask.getCountdown()));
        }

    }

}
