package net.warvale.core.commands.game;

import net.warvale.core.commands.AbstractCommand;
import net.warvale.core.exceptions.CommandException;
import net.warvale.core.tasks.BossbarCountdownTask;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestStartCommand extends AbstractCommand {

    public TestStartCommand() {
        super("teststart", "");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can execute this command.");
        }

        new BossbarCountdownTask().runTaskTimer(plugin, 20, 20);

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

}
