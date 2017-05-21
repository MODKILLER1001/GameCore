package net.warvale.core.game.start;

import net.warvale.core.Main;
import net.warvale.core.game.MatchInfo;
import net.warvale.core.map.GameMap;
import net.warvale.core.tasks.StartTask;
import net.warvale.core.utils.chat.ChatUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Draem on 5/21/2017.
 */
public class Initialization {

    private GameMap map;

    public Initialization(GameMap map, MatchInfo info) {
        try {
            Main.get().getLogger().log(Level.INFO, "Beginning Conquest match on \"" + map.getName() + "\"." +
                    "\nPlayers: " + info.PLAYER_COUNT +
                    "\n-> Red: " + info.RED +
                    "\n-> Blue: " + info.BLUE +
                    "\nMap Info: " +
                    "\n->Author: " + StringUtils.join(map.getAuthors(), ", "));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        this.map = map;
    }

    public void startGame() {
        List<Player> players = new ArrayList<>();
        List<Player> red = new ArrayList<>();
        List<Player> blue = new ArrayList<>();
        Main.getTeams().getBlueTeam().getPlayers().stream().filter(OfflinePlayer::isOnline).forEach(offlinePlayer -> {
            players.add(offlinePlayer.getPlayer());
            blue.add(offlinePlayer.getPlayer());
        });
        Main.getTeams().getRedTeam().getPlayers().stream().filter(OfflinePlayer::isOnline).forEach(offlinePlayer -> {
            players.add(offlinePlayer.getPlayer());
            red.add(offlinePlayer.getPlayer());
        });
        players.forEach(player -> {
            player.addPotionEffects(
                    Arrays.asList(
                            new PotionEffect(PotionEffectType.SLOW, 100000, 128, false),
                            new PotionEffect(PotionEffectType.JUMP, 100000, 250, false)));
            String team = player.getScoreboard().getTeam(player.getName()).getName();
            player.sendMessage((String[]) Arrays.asList("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",
                    ChatUtils.divider,
                    "You are on team " + (team.equals("blue") ? ChatUtils.blue + "blue!" : ChatUtils.red + "red!"),
                    "\n",
                    ChatUtils.gray + "Your goal is to destroy the other teams " + ChatUtils.yellow + "core" + ChatUtils.gray + " 20 times!",
                    "The game is divided into " + ChatUtils.red + "5" + ChatUtils.gray + " stages!",
                    ChatUtils.yellow + "1." + ChatUtils.gray + " During the first " + ChatUtils.red + "20" + ChatUtils.gray + " minutes of the game, your core is invulnerable!",
                    ChatUtils.yellow + "2." + ChatUtils.gray + " At the end of the first stage, a boss spawns in the middle of the map!",
                    ChatUtils.yellow + "3." + ChatUtils.gray + " After that, diamonds will start to appear at middle. Diamonds can be used to craft the strongest armor in the game!",
                    ChatUtils.yellow + "4." + ChatUtils.yellow + " Siege mode!" + ChatUtils.gray + " During this stage of the game, core breaking is 2x as powerful1!",
                    ChatUtils.yellow + "5." + ChatUtils.gray + " During the last stage of the game, cores will be broken instantly!",
                    ChatUtils.divider).toArray());

            new StartTask(this.map).runTaskTimer(Main.get(), 0, 20);
        });

    }

}
