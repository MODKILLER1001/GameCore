package net.warvale.core.game.start;

import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.maps.VoteMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


/**
 *  Created by AAces on 6/2/17
 */
public class VoteCommand extends AbstractCommand {
    public VoteCommand() {
        super("vote", "");
    }
    @Override
    public boolean execute(CommandSender player, String[] args) throws CommandException {

        if (!(player instanceof Player)) {
            player.sendMessage("Only players can use this command!");
            return false;
        }
        Player sender = (Player) player;
        if (!GameStart.votingActive) {
            sender.sendMessage(ChatColor.RED + "Voting is not currently active!");
            return false;
        }

        if(!(Main.getTeams().getBlueTeam().hasEntry(sender.getName()) || Main.getTeams().getRedTeam().hasEntry(sender.getName()))){
            sender.sendMessage(ChatColor.RED + "You must be on a team to vote!");
            return false;
        }
        VoteMenu.getMenu(sender).show(sender);

        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
