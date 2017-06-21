package net.warvale.core.commands.game;

import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.spec.ClassSelect;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by AAces on 6/20/2017.
 */
public class ClassSelectCommand extends AbstractCommand {

    public ClassSelectCommand() {
        super("class", null);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command");
        }
        Player player = (Player) sender;
        new ClassSelect(Main.get()).openGUI(player);
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
