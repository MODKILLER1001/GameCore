package net.warvale.core.connect;

import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.scoreboards.GameScoreboard;
import net.warvale.core.game.scoreboards.LobbyScoreboard;
import net.warvale.core.map.MapLocations;
import net.warvale.core.maps.VoteMenu;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.spec.Preferences;
import net.warvale.core.utils.mc.items.ItemStackBuilder;
import net.warvale.staffcore.bossbar.BarManager;
import net.warvale.staffcore.users.User;
import net.warvale.staffcore.users.UserManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.boss.BarColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;
import org.bukkit.scheduler.BukkitRunnable;


public class JoinServer implements Listener {

    public static ItemStack[] generateSpawnInventory(int inventorysize) {
        ItemStack[] is = new ItemStack[inventorysize];
        is[MAPSLOT] = mapselection.build();
        return is;
    }

 
    private static ItemStackBuilder mapselection = new ItemStackBuilder(Material.PAPER).withName(ChatColor.DARK_AQUA + "Maps").withLore(ChatColor.GRAY + "Click to vote for a map");
    private static int KITSLOT = 0, MAPSLOT = 1;

    public JoinServer(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();

    	event.setJoinMessage("");
        String playerName = event.getPlayer().getName();
        p.getInventory().clear();
        p.sendMessage(ChatColor.GRAY + "Welcome back to " + ChatColor.DARK_RED + "Warvale!");
        //Main.getTeams().getSpectatorTeam().addEntry(p.getName());
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
        for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
			if(!Preferences.noJoinMessages.contains(onlinePlayer.getName()) && onlinePlayer.getName() != event.getPlayer().getName()){
				onlinePlayer.sendMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " joined.");
			}
		}
        

        /*if (Main.getTeams().getSpectatorTeam().getEntries().contains(p.getName())) {
            p.setAllowFlight(true);
            p.setGameMode(GameMode.ADVENTURE);
        }*/


        p.setAllowFlight(true);
        p.setGameMode(GameMode.ADVENTURE);
        if (Game.getInstance().isState(State.INGAME)){
            int minPlayers = Game.getInstance().getMinPlayers() - Bukkit.getOnlinePlayers().size();
            BarManager.broadcastSound(Sound.BLOCK_NOTE_BASS);
            BarManager.broadcast(BarColor.GREEN, ChatColor.RED +
                    String.valueOf(minPlayers) + ChatColor.DARK_GREEN +
                    " more players needed to start the game!");
        }
        LobbyScoreboard.getInstance().addScoreboard(event.getPlayer());
        LobbyScoreboard.getInstance().newScoreboard(event.getPlayer());

        if (Game.getInstance().isState(State.LOBBY)) {
            p.teleport(MapLocations.getObjectLocation(null, null, null)); //having null in all three returns the lobby center
            p.getInventory().setContents(generateSpawnInventory(4 * 9));
        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (Game.getInstance().getState() == State.LOBBY &&
                e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_BLOCK) {
            int slot = p.getInventory().getHeldItemSlot();
            if (slot == MAPSLOT) {
                VoteMenu.getMenu(p).show(p);
            } else if (slot == KITSLOT) {
                p.sendMessage("Function not implemented yet");
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLogin(PlayerLoginEvent event) {
        //check for debug mode and permissions
        Player player = event.getPlayer();

        if (Game.getInstance().isDebugEnabled() && !player.hasPermission("warvale.testing")) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("You are not authorized to test games.");
            return;
        }

        event.setResult(PlayerLoginEvent.Result.ALLOWED);
    }

}
