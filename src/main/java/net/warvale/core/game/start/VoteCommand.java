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
            sender.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Usage:" + ChatColor.GRAY + " /vote <map number>");
            return false;
        }

        switch (args[0]){
            case "1":
                sender.sendMessage(ChatColor.BLUE + "You voted for: " + ChatColor.GOLD + "REDWOOD FOREST" + ChatColor.BLUE + "!");
                GameStart.voted.add(sender.getName());
                GameStart.votes.put("redwood_forest", GameStart.votes.get("redwood_forest") + 1);
                break;
            case "2":
                sender.sendMessage(ChatColor.BLUE + "You voted for: " + ChatColor.GOLD + "VOLCANO ISLAND" + ChatColor.BLUE + "!");
                GameStart.voted.add(sender.getName());
                GameStart.votes.put("volcano_island", GameStart.votes.get("volcano_island") + 1);
                break;
            case "3":
                sender.sendMessage(ChatColor.BLUE + "You voted for: " + ChatColor.GOLD + "PAGODA EVERGLADE" + ChatColor.BLUE + "!");
                GameStart.voted.add(sender.getName());
                GameStart.votes.put("pagoda_everglade", GameStart.votes.get("pagoda_everglade") + 1);
                break;
            case "4":
                sender.sendMessage(ChatColor.BLUE + "You voted for: " + ChatColor.GOLD + "EXTRATERRESTRIAL" + ChatColor.BLUE + "!");
                GameStart.voted.add(sender.getName());
                GameStart.votes.put("extraterrestrial", GameStart.votes.get("extraterrestrial") + 1);
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Unknown map!");
                break;
        }
         return false;
        }

        return false;
    }
}
