package net.warvale.core.classes;


import net.md_5.bungee.api.ChatColor;
import net.warvale.core.Main;
import net.warvale.core.utils.mc.chat.MessageUtil;
import net.warvale.core.utils.mc.items.ItemStackBuilder;
import net.warvale.core.utils.mc.menu.Menu;
import net.warvale.staffcore.users.User;
import net.warvale.staffcore.users.UserManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassMenu extends Menu {

    private static final String display = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Class Selector";
    private static HashMap<Integer, Class> slots = new HashMap<>();
    private static final Sound click = Sound.ENTITY_ARROW_HIT;

    public ClassMenu() {
        super(display, 45);
        Main.get().registerMenu(this);
        update();
    }

    public void click(Player player, ClickType clickType, int slot, ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) return;

        Class clazz = ClassManager.getClassByName(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));

        if (clazz == null) {
            return;
        }

        if (!ClassShop.hasPurchased(player, clazz)) {
            User user = UserManager.getUser(player);

            if (user.getEmbers() < clazz.getPrice()) {
                player.sendMessage(ChatColor.RED + "You can not afford that class at this time.");
                return;
            } else if (user.getEmbers() >= clazz.getPrice()) {
                user.setEmbers(user.getEmbers() - clazz.getPrice());
                ClassShop.purchase(player, clazz);
                player.sendMessage(ChatColor.GREEN + "You have bought the " + clazz.getName() + " class!");
            }

        }

        clazz.addMember(player);

        player.sendMessage(ChatColor.GRAY + "You have successfully chosen the " + ChatColor.YELLOW + clazz.getName()
                + ChatColor.GRAY + " class!");

        player.playSound(player.getLocation(), click, 1f, 1f);
        player.closeInventory();

    }


    public ItemStack[] generate() {
        ItemStack[] stack = new ItemStack[getInventory().getSize()];

        int i = 0;
        for (Map.Entry<String, Class> clazzSet : ClassManager.classes.entrySet()) {
            Class clazz = clazzSet.getValue();

            ItemStackBuilder classStack = new ItemStackBuilder(clazz.getItem())
                    .withName(ChatColor.AQUA + clazz.getName())
                    .withLore(ChatColor.translateAlternateColorCodes('&', "&7PRICE: &e" + clazz.getPrice()))
                    .withLore(ChatColor.GRAY + "ABILITY: " + ChatColor.YELLOW + clazz.getAbility());

            stack[i] = classStack.build();
            slots.put(i, clazz);
            i++;
        }

        return stack;
    }

}
