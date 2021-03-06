package net.warvale.core.maps;

import com.google.common.base.Joiner;
import net.warvale.core.Main;
import net.warvale.core.game.start.VoteRandomMap;
import net.warvale.core.utils.mc.chat.MessageUtil;
import net.warvale.core.utils.mc.items.EnchantGlow;
import net.warvale.core.utils.mc.items.ItemStackBuilder;
import net.warvale.core.utils.mc.menu.Menu;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class VoteMenu extends Menu{
    private static final String display = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Vote";
    private static final Sound click = Sound.ENTITY_ARROW_HIT;
    private static final ItemStackBuilder builder = new ItemStackBuilder(Material.PAPER);
    private static final List<GameMap> mapList = new ArrayList<>();
    private static ItemStack[] contents;
    private static final Map<UUID, GameMap> votes = new HashMap<>();
    private static final Map<UUID, VoteMenu> cached = new HashMap<>();
    private static final Map<GameMap,Integer> votesbymap = new HashMap<>();

    public static GameMap calculateMap() {
        int current = 0;
        GameMap currentmap = null;
        for (Map.Entry<GameMap, Integer> entry : votesbymap.entrySet()) {
            if(entry.getValue() > current || (entry.getValue() == current)){
                currentmap = entry.getKey();
                current = entry.getValue();
            }
        }
        return currentmap;
    }

    public static VoteMenu getMenu(Player p){
        VoteMenu menu = cached.containsKey(p.getUniqueId()) ? cached.get(p.getUniqueId()) : new VoteMenu();
        cached.put(p.getUniqueId(), menu);
        return menu;
    }

    public static Collection<GameMap> getVotes(){
        return votes.values();
    }

    public static void removeMenu(Player p){
        if(cached.containsKey(p.getUniqueId())) {
            cached.remove(p.getUniqueId()).deregister();
        }
    }

    public static int getAmountOfVotes(){
        return votes.size();
    }

    public static void wipeClean(){
        contents = null;
        for(VoteMenu menu:cached.values()){
            Main.get().unregisterMenu(menu);
        }
        votes.clear();
        mapList.clear();
        cached.clear();
        votesbymap.clear();
    }

    public static ItemStack[] generateInventory() {
        //If it has been reset we can add
        if(mapList.isEmpty()){
            Collections.shuffle(GameMap.getMaps());
            int size = GameMap.getMaps().size() > 5 ? 5 : GameMap.getMaps().size();
            for(int i = 0; i < size; i++){
                mapList.add(GameMap.getMaps().get(i));
            }
            for(GameMap map: mapList){
                votesbymap.put(map, 0);
            }
        }


        ItemStack[] is = new ItemStack[9];

        ItemStackBuilder grayscale = new ItemStackBuilder(Material.STAINED_GLASS_PANE).withColor(DyeColor.GRAY);

        int i = 2;

        for (GameMap map : mapList) {
            ItemStackBuilder builder = VoteMenu.builder.clone().withName(map.getName()).withLore("").withLore(ChatColor.BLUE + "Votes: " + ChatColor.AQUA + votesbymap.get(map)).withLore("");
            is[i] = builder.build();
            i++;
        }

        for(i = 0;i < is.length; i++){
            if(is[i] == null)is[i] = grayscale.build();
        }

        return is;
    }

    public static void updateAll(){
        contents = generateInventory();
        for(VoteMenu menu: cached.values()){
            menu.update();
        }
    }

    private VoteMenu() {
        super(display, 9);
        Main.get().registerMenu(this);
        update();
    }

    public void deregister(){
        if(uuid != null && votes.containsKey(uuid)){
            GameMap map = votes.remove(uuid);
            if(mapList.contains(map)) {
                votesbymap.put(map, votesbymap.get(map) - 1);
            }
        }
        Main.get().unregisterMenu(this);
    }

    private UUID uuid = null;


    public void click(Player player, ClickType type, int slot, ItemStack itemStack) {
        if (slot >= 2 || slot < mapList.size() + 2) {
            GameMap map = mapList.get(slot - 2);
            if (votes.get(player.getUniqueId()) == map) {
                votes.remove(player.getUniqueId());
                votesbymap.put(map, votesbymap.get(map) - 1);
                player.spigot().sendMessage(new MessageUtil.MessageBuilder("You cancelled your vote for ").color(ChatColor.BLUE).append(map.getName()).create());
            } else {
                if(votes.containsKey(player.getUniqueId())){
                    GameMap last = votes.get(player.getUniqueId());
                    if(mapList.contains(last)) {
                        votesbymap.put(last, votesbymap.get(last) - 1);
                    }
                }
                votes.put(player.getUniqueId(), map);
                votesbymap.put(map, votesbymap.get(map) + 1);
                player.spigot().sendMessage(new MessageUtil.MessageBuilder("You have voted for ").color(ChatColor.BLUE).append(map.getName()).create());
            }
            player.playSound(player.getLocation(), click, 1f, 1f);
            uuid = player.getUniqueId();
            updateAll();
        }
    }

    @Override
    public ItemStack[] generate() {
        if(contents == null) {
            contents = generateInventory();
        }
        ItemStack[] generate = contents.clone();

        if(uuid != null && votes.containsKey(uuid)) {
            int slot = mapList.indexOf(votes.get(uuid)) + 2;
            ItemStackBuilder replacewith = new ItemStackBuilder(generate[slot].clone());
            replacewith.withType(Material.BOOK);
            replacewith.withLore("",ChatColor.GOLD + "You have voted for this map");
            generate[slot] = EnchantGlow.addGlow(replacewith.build());
        }
        return generate;
    }
}
