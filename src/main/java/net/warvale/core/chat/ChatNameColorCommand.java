package net.warvale.core.chat;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 6/10/2017.
 */
public class ChatNameColorCommand extends AbstractCommand {

    public ChatNameColorCommand() {
        super("chatnamecolor", "");
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException{
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by players");
            return false;
        }
        Player player = (Player) sender;
        ChatNameColorGUI.colorGUI(player);
        return true;
    }
    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}