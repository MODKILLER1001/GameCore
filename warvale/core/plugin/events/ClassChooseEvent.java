package warvale.core.plugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import warvale.core.plugin.classes.Class;


/**
 * Created by Draem on 5/20/2017.
 */
public class ClassChooseEvent extends PlayerEvent {

    Class clazz;
    Player player;

    public ClassChooseEvent(Player who, Class clazz) {
        super(who);
        this.clazz = clazz;
        this.player = who;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public Class getClazz() {
        return clazz;
    }
}
