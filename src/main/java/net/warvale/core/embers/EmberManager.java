package net.warvale.core.embers;

import net.warvale.core.Main;
import net.warvale.core.utils.sql.SQLUtil;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Draem on 6/4/2017.
 */
public class EmberManager {

    public static void giveEmbers(Player player, Integer amount) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(Main.getDB(), "users", "embers", new SQLUtil.Where(new SQLUtil.WhereVar("uuid", player.getUniqueId().toString()).getWhere()));
        set.next();
        SQLUtil.update(Main.getDB(), "users", "embers", set.getInt("embers") + amount, new SQLUtil.Where(new SQLUtil.WhereVar("uuid", player.getUniqueId().toString()).getWhere()));
    }

    public static void takeEmbers(Player player, Integer amount) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(Main.getDB(), "users", "embers", new SQLUtil.Where(new SQLUtil.WhereVar("uuid", player.getUniqueId().toString()).getWhere()));
        set.next();
        SQLUtil.update(Main.getDB(), "users", "embers", set.getInt("embers") - amount, new SQLUtil.Where(new SQLUtil.WhereVar("uuid", player.getUniqueId().toString()).getWhere()));
    }

}
