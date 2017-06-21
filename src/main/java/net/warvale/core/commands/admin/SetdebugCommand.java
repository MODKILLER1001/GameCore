package net.warvale.core.commands.admin;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.config.ConfigManager;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetdebugCommand extends AbstractCommand {

    public SetdebugCommand() {
        super("setdebug", "<debug>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        if (args.length == 0) {
            return false;
        }

        try {
            boolean debug = parseBoolean(args[0]);

            Game.getInstance().setDebug(debug);
            MessageManager.broadcast(PrefixType.MAIN, "Debug mode is now set to " + debug);

        } catch (Exception ex) {
            throw new CommandException("Invalid option");
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> toReturn = new ArrayList<>();

        if (args.length == 1) {
            toReturn.add("enable");
            toReturn.add("disable");
        }

        return toReturn;
    }

}
