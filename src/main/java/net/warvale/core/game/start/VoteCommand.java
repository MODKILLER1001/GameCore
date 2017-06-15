package net.warvale.core.game.start;

import net.warvale.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 *  Created by AAces on 6/2/17
 */
public class VoteCommand implements CommandExecutor {
    public boolean onCommand(CommandSender player, Command cmd, String label, String args[]){
        if (label.equalsIgnoreCase("vote")) {
            if (!(player instanceof Player)) {
                player.sendMessage("Only players can use this command!");
                return false;
            }

            Player sender = (Player) player;

        if (!GameStart.votingActive) {
            sender.sendMessage(ChatColor.RED + "Voting is not currently active!");
            return false;
        }

        if(!(Main.getTeams().getBlueTeam().hasPlayer(sender) || Main.getTeams().getRedTeam().hasPlayer(sender))){
            sender.sendMessage(ChatColor.RED + "You must be on a team to vote!");
            return false;
        }

        if (GameStart.voted.contains(sender.getName())){
            sender.sendMessage(ChatColor.RED + "You have already voted!");
            return false;
        }

        if (args.length == 0){
            //open inv
        }

         return false;
        }

        return false;
    }
}
