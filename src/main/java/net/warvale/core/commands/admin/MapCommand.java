package net.warvale.core.commands.admin;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.map.GameMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MapCommand extends AbstractCommand {

    public MapCommand() {
        super("map", "<create|addauthor|setlobby|addspawn> <map>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        if (args.length == 0) {
            return false;
        }

        if (args[0].equalsIgnoreCase("create")) {
            String map = args[1];

            try {
                GameMap gameMap = new GameMap(map);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return true;
        }

        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

}
