package net.warvale.core.utils.mc.menu;

import org.bukkit.inventory.ItemStack;

public class InventoryBuilder implements Cloneable {
    private ItemStack[] stack;

    private InventoryBuilder() {

    }

    public InventoryBuilder(int size) {
        stack = new ItemStack[size];
    }

    public InventoryBuilder withSlot(ItemStack is, int slot) {
        stack[slot] = is;
        return this;
    }

    public InventoryBuilder clear() {
        stack = new ItemStack[stack.length];
        return this;
    }

    public ItemStack atSlot(int slot) {
        return stack[slot];
    }

    public ItemStack[] build() {
        return stack.clone();
    }

    public InventoryBuilder clone() {
        InventoryBuilder builder = new InventoryBuilder();
        builder.stack = build();
        return builder;
    }
}
