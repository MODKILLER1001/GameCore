package net.warvale.core.classes;

import net.warvale.core.Main;
import net.warvale.core.embers.EmberManager;
import net.warvale.core.utils.sql.SQLConnection;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by CommandFox on 6/20/2017.
 */
public class ClassShop {
    public static void purchase(Player player, Class clazz) throws SQLException, ClassNotFoundException {
        EmberManager.takeEmbers(player, clazz.getPrice());

        SQLConnection connection = Main.getDB();
        connection.executeSQL(
                "INSERT INTO `classes` (`uuid`, `class`) VALUES ('" + player.getUniqueId().toString() + "', '" + clazz.getName() + "')");
    }

    public static boolean hasPurchased(Player player, Class clazz) throws SQLException, ClassNotFoundException {
        Connection connection = Main.getDB().getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM `classes` where `uuid` = " + player.getUniqueId().toString() + " AND  `class` = " + clazz.getName());

        ResultSet rs = stmt.executeQuery();
        if(!rs.next()){
            return false;
        }
        return true;
    }
}

