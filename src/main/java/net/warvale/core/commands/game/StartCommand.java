package net.warvale.core.commands.game;


import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StartCommand extends AbstractCommand {


    public StartCommand() {
        super("start", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        Player player = (Player) sender;

        player.sendMessage(ChatColor.GRAY + "Starting the game automatically..");

        Game.start(null, null);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

}
