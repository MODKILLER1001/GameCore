package net.warvale.core.commands.game;

import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class ClassSelectCommand extends AbstractCommand {

    public ClassSelectCommand() {
        super("class", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command");
        }
        Player player = (Player) sender;

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
