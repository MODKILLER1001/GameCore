package net.warvale.core.commands.game;


import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LeaveCommand extends AbstractCommand {

    public LeaveCommand() {
        super("leave", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        Player player = (Player) sender;

        if (Main.getTeams().getRedTeam().getEntries().contains(player.getName())) {
            player.sendMessage(ChatColor.GRAY + "You may not leave the game at this time.");
        } else if (Main.getTeams().getBlueTeam().getEntries().contains(player.getName())) {
            player.sendMessage(ChatColor.GRAY + "You may not leave the game at this time.");
        } else {
            player.sendMessage(ChatColor.GRAY + "You must be on a team to leave the game.");
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

}
