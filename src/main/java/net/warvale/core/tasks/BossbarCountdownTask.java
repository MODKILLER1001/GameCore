package net.warvale.core.tasks;

import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.game.logic.StageSystem.Stages;
import net.warvale.core.game.start.GameStart;
import net.warvale.core.map.GameMap;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.utils.chat.ChatUtils;
import net.warvale.core.utils.dates.DateUtils;
import net.warvale.staffcore.bossbar.BarManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;


public class BossbarCountdownTask extends BukkitRunnable {

    private static int countdown = 1 * 60 + 1;
    private static String chosenMap;
    private String coordsRed;
    private String coordsBlue;

    public BossbarCountdownTask() {
        countdown = 1 * 60 + 1;
    }

    @Override
    public void run(){
        countdown = countdown - 1;
        Game.getInstance().setState(State.COUNTDOWN);
        BarManager.getAnnounceBar().setVisible(true);
        if (countdown == 15){
            GameStart.voteTally();
            switch (GameStart.map.getName()){
                case "redwood":
                    chosenMap = ChatColor.RED + "Redwood Forest";
                    break;
                case "pagoda":
                    chosenMap = ChatColor.RED + "Pagoda Everglade";
                    break;
                case "volcano":
                    chosenMap = ChatColor.RED + "Volcano Island";
                    break;
                case "extraterrestrial":
                    chosenMap = ChatColor.RED + "Extraterrestrial";
                    break;
            }
            MessageManager.broadcast(PrefixType.MAIN, chosenMap + ChatColor.GRAY + " has been chosen as the map you will be playing on!");
        }
        if (countdown == 10){
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + ChatColor.RED + countdown + ChatColor.GRAY + (countdown == 1 ? " second." : " seconds."));

            for (Player player : GameStart.inGame){
                player.addPotionEffects(
                        Arrays.asList(
                                new PotionEffect(PotionEffectType.SLOW, 100000, 128, false, false),
                                new PotionEffect(PotionEffectType.JUMP, 100000, 250, false, false)));
                player.sendMessage((String[]) Arrays.asList("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",
                        ChatUtils.divider,
                        "\n",
                        ChatUtils.gray + "Your goal is to destroy the other teams " + ChatUtils.yellow + "core" + ChatUtils.gray + " 20 times!",
                        ChatUtils.gray + "The game is divided into " + ChatUtils.red + "5" + ChatUtils.gray + " stages!",
                        ChatUtils.yellow + "1." + ChatUtils.gray + " During the first " + ChatUtils.red + "20" + ChatUtils.gray + " minutes of the game, your core is invulnerable!",
                        ChatUtils.yellow + "2." + ChatUtils.gray + " At the end of the first stage, a boss spawns in the middle of the map!",
                        ChatUtils.yellow + "3." + ChatUtils.gray + " After that, diamonds will start to appear at middle. Diamonds can be used to craft the strongest armor in the game!",
                        ChatUtils.yellow + "4." + ChatUtils.yellow + " Siege mode!" + ChatUtils.gray + " During this stage of the game, core breaking is 2x as powerful1!",
                        ChatUtils.yellow + "5." + ChatUtils.gray + " During the last stage of the game, cores will be broken instantly!",
                        "\n",
                        ChatUtils.divider).toArray());
            }
        }
        if (countdown <= 9 && countdown >= 1){
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + ChatColor.RED + countdown + ChatColor.GRAY + (countdown == 1 ? " second." : " seconds."));
            BarManager.broadcastSound(Sound.BLOCK_NOTE_PLING);
        }
        if (countdown <= 0){
            new Stages().initStages();
            this.cancel();
            MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "The game has begun on " + ChatColor.RED + GameStart.map.getName() + ChatColor.GRAY + "!");
            //Insert method to start the game.
            BarManager.getAnnounceBar().setVisible(false);
            for (Player player : GameStart.inGame){
                player.removePotionEffect(PotionEffectType.SLOW);
                player.removePotionEffect(PotionEffectType.JUMP);

            }

            for (Player player : GameStart.teamBlue){
                //tp player to blue spawn in the map GameStart.map
            }
            for (Player player : GameStart.teamRed){
                //tp player to red spawn in the map GameStart.map
            }

            return;
        }
        BarManager.getAnnounceBar().setTitle(ChatColor.DARK_RED + "Conquest " + ChatColor.GRAY + "starts in " + DateUtils.secondsToString(countdown));
        BarManager.getAnnounceBar().setProgress((float)countdown/(float)(60 * 5));
        BarManager.getAnnounceBar().setColor(BarColor.BLUE);
    }

    public static int getCountdown() {
        return countdown;
    }
}
