package net.warvale.core.hooks;

import de.robingrether.idisguise.api.DisguiseAPI;
import net.warvale.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class DisguiseHook {

    private static DisguiseHook instance;
    private DisguiseAPI api = null;

    //temp for testing
    private List<String> randomPlayers = Arrays.asList("Draem", "archybot", "Fruitified", "MODKILLER1001",
            "Pixelific", "rxwrr", "Reesb", "Alihsoccer", "lenavision", "Bluesnowflakes");

    public static DisguiseHook getInstance() {
        if (instance == null) {
            instance = new DisguiseHook();
        }
        return instance;
    }

    public void setup() {
        Plugin disguiseTest = Bukkit.getServer().getPluginManager().getPlugin("iDisguise");
        if (disguiseTest == null || !disguiseTest.isEnabled())
        {
            Main.get().getLogger().log(Level.WARNING, "iDisguise is not present, so the integration was disabled.");
            return;
        }

        try
        {
            this.api = Bukkit.getServer().getServicesManager().getRegistration(DisguiseAPI.class).getProvider();
        }
        catch (Exception e)
        {
            this.api = null;
            return;
        }

        Main.get().getLogger().log(Level.INFO, "Successfully hooked into iDisguise.");
    }

    public boolean isEnabled()
    {
        return !(this.api == null);
    }

    public DisguiseAPI getAPI() {
        return this.api;
    }

    public List<String> getRandomPlayers() {
        return randomPlayers;
    }


}
