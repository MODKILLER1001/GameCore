package net.warvale.core.commands.admin;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.game.Game;
import net.warvale.core.game.State;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetStateCommand extends AbstractCommand {

    public SetStateCommand() {
        super("setstate", "<state>");
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
            State state = State.valueOf(args[0].toUpperCase());

            Game.getInstance().setState(state);
            MessageManager.broadcast(PrefixType.MAIN, "The state has been set to " + state.toString());

        } catch (Exception ex) {
            throw new CommandException("That is an invalid state.");
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> toReturn = new ArrayList<>();

        if (args.length == 1) {
            for(State state : State.values()) {
                toReturn.add(state.toString());
            }
        }

        return toReturn;
    }

}
