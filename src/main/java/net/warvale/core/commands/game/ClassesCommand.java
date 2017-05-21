package net.warvale.core.commands.game;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ehock on 5/21/2017.
 */
public class ClassesCommand extends AbstractCommand {

    public ClassesCommand() {
        super("classes", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        sender.sendMessage(ChatColor.AQUA + "All classes: " + ChatColor.GRAY + "Soldier," + ChatColor.WHITE
                + " Archer," + ChatColor.GRAY + " Assassin," + ChatColor.WHITE + " Miner," + ChatColor.GRAY
                + " Spy," + ChatColor.WHITE + " Technician," + ChatColor.GRAY + " Musician," + ChatColor.WHITE
                + " Pyromaniac," + ChatColor.GRAY + " Necromancer," + ChatColor.WHITE + " Earthbender,"
                + ChatColor.GRAY + " Medic");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
