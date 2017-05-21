package net.warvale.core.commands.game;


import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class VersionCommand extends AbstractCommand {

    public VersionCommand() {
        super("gamever", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(ChatColor.GRAY + "Gamecore is running on version 1.1.6");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

}
