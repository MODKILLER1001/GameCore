package warvale.core.plugin.kits;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import warvale.core.plugin.Main;

public class KitItems implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if (cs instanceof Player) {
            Player sender = (Player) cs;

            PlayerInventory inv = sender.getInventory();

            if (inv.getHelmet() != null) {
                sender.sendMessage(ChatColor.GRAY + "You've already chosen a kit.");
                return true;
            }

            if (Main.getRedTeam().getEntries().contains(sender.getName())
                    || Main.getBlueTeam().getEntries().contains(sender.getName())) {
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                    case "soldier":

                        sender.getInventory().clear();
                        sender.sendMessage(ChatColor.GRAY + "Equipped kit " + ChatColor.DARK_RED + "Soldier");

                        ItemStack swordiron_soldier = new ItemStack(Material.IRON_SWORD, 1);
                        swordiron_soldier.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

                        ItemStack bow_soldier = new ItemStack(Material.BOW, 1);
                        bow_soldier.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

                        ItemStack goldapple_soldier = new ItemStack(Material.GOLDEN_APPLE, 1);

                        ItemStack arrow_soldier = new ItemStack(Material.ARROW, 4);

                        ItemStack helmetleather_soldier = new ItemStack(Material.LEATHER_HELMET, 1);

                        helmetleather_soldier.addEnchantment(Enchantment.BINDING_CURSE, 1);

                        if (Main.getRedTeam().getEntries().contains(sender.getName())) {
                            LeatherArmorMeta lh = (LeatherArmorMeta) helmetleather_soldier.getItemMeta();
                            lh.setColor(Color.fromRGB(183, 0, 36));
                            helmetleather_soldier.setItemMeta(lh);
                        }

                        if (Main.getBlueTeam().getEntries().contains(sender.getName())) {
                            LeatherArmorMeta lh = (LeatherArmorMeta) helmetleather_soldier.getItemMeta();
                            lh.setColor(Color.fromRGB(0, 147, 163));
                            helmetleather_soldier.setItemMeta(lh);
                        }

                        ItemStack cplateiron = new ItemStack(Material.IRON_CHESTPLATE, 1);
                        ItemStack pantsiron = new ItemStack(Material.IRON_LEGGINGS, 1);
                        ItemStack bootsiron = new ItemStack(Material.IRON_BOOTS, 1);

                        inv.addItem(swordiron_soldier);
                        inv.addItem(bow_soldier);
                        inv.addItem(goldapple_soldier);
                        inv.addItem(arrow_soldier);
                        inv.setHelmet(helmetleather_soldier);
                        inv.setChestplate(cplateiron);
                        inv.setLeggings(pantsiron);
                        inv.setBoots(bootsiron);

                        break;

                    case "hunter":

                        sender.getInventory().clear();
                        sender.sendMessage(ChatColor.GRAY + "Equipped kit " + ChatColor.DARK_RED + "Hunter");

                        ItemStack swordstone_hunter = new ItemStack(Material.STONE_SWORD, 1);
                        swordstone_hunter.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

                        ItemStack bow_hunter = new ItemStack(Material.BOW, 1);

                        bow_hunter.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                        bow_hunter.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
                        bow_hunter.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
                        bow_hunter.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);

                        ItemStack goldapple_hunter = new ItemStack(Material.GOLDEN_APPLE, 1);

                        ItemStack arrow_hunter = new ItemStack(Material.ARROW, 1);

                        ItemStack helmetleather_hunter = new ItemStack(Material.LEATHER_HELMET, 1);

                        helmetleather_hunter.addEnchantment(Enchantment.BINDING_CURSE, 1);

                        if (Main.getRedTeam().getEntries().contains(sender.getName())) {
                            LeatherArmorMeta lh = (LeatherArmorMeta) helmetleather_hunter.getItemMeta();
                            lh.setColor(Color.fromRGB(183, 0, 36));
                            helmetleather_hunter.setItemMeta(lh);
                        }

                        if (Main.getBlueTeam().getEntries().contains(sender.getName())) {
                            LeatherArmorMeta lh = (LeatherArmorMeta) helmetleather_hunter.getItemMeta();
                            lh.setColor(Color.fromRGB(0, 147, 163));
                            helmetleather_hunter.setItemMeta(lh);
                        }

                        ItemStack cplatechain_hunter = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
                        ItemStack pantschain_hunter = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
                        ItemStack bootschain_hunter = new ItemStack(Material.CHAINMAIL_BOOTS, 1);

                        inv.addItem(swordstone_hunter);
                        inv.addItem(bow_hunter);
                        inv.addItem(goldapple_hunter);
                        inv.addItem(arrow_hunter);
                        inv.setHelmet(helmetleather_hunter);
                        inv.setChestplate(cplatechain_hunter);
                        inv.setLeggings(pantschain_hunter);
                        inv.setBoots(bootschain_hunter);

                        break;
                    default:
                        sender.sendMessage(ChatColor.GRAY + "Kit name isn't valid! Available kits: Soldier, Hunter");
                        break;
                    }
                } else {
                    sender.sendMessage(ChatColor.GRAY + "Incorrect usage. Correct usage: /kit <kitname>");
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.GRAY + "You must be on a team to select a kit!");
            }
            return true;
        }
        return false;
    }
}
