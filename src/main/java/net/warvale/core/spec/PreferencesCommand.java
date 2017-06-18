package net.warvale.core.spec;

import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 6/10/2017.
 */
public class PreferencesCommand extends AbstractCommand {


    public PreferencesCommand() {
        super("settings", "");
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by players");
            return false;
        }
        Player player = (Player) sender;
        new Preferences(Main.get()).tsGUI(player);
        return true;
    }
    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

}