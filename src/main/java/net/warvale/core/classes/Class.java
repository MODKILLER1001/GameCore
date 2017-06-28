package net.warvale.core.classes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Draem on 5/12/2017.
 */
public class Class {

    private String name;
    private Integer price;
    private List<String> description;
    private ItemStack item;
    private String ability;
    private Boolean defaultClass;

    private List<UUID> players;

    public Class(String name, Integer price, List<String> description, ItemStack item, String ability, Boolean defaultClass) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.item = item;
        this.ability = ability;
        this.defaultClass = defaultClass;

        this.players = new ArrayList<>();

        ClassManager.initClass(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getDescription() {
        return this.description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getAbility() {
        return this.ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public boolean addMember(Player player) {
        if (!ClassManager.hasClass(player)) {
            this.players.add(player.getUniqueId());
            return true;
        }
        return false;
    }

    public void setDefaultClass(boolean value){
        this.defaultClass = value;
    }

    public Boolean isDefaultClass(){
        return this.defaultClass;
    }

    public List<UUID> getMembers() {
        return this.players;
    }
}
