package net.warvale.core.items;

import net.warvale.core.Main;
import net.warvale.core.utils.mc.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        if(inv != null && e.getClickedInventory() != null) {
            for (Menu menu : Main.get().listMenu()) {
                if (menu.getInventory().equals(inv)) {
                    e.setCancelled(true);
                    if (e.getClickedInventory().equals(inv)) {
                        menu.click(player, e.getClick(), e.getSlot(), e.getCurrentItem());
                    }
                }
            }
        }
    }



}
