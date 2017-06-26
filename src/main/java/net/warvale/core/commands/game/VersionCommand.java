package net.warvale.core.commands.game;


import net.warvale.core.Main;
import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.utils.chat.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class VersionCommand extends AbstractCommand {

    public VersionCommand() {
        super("gamecore", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(ChatUtils.yellow + "GameCore is currently running on version " + ChatUtils.gold + Main.get().getVersion() + ChatUtils.yellow + ".");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

}
