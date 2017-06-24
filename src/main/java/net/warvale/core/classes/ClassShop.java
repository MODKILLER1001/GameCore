package net.warvale.core.classes;

import net.warvale.core.Main;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassShop {

    public static void purchase(Player player, Class clazz) {
        try {
            Connection connection = Main.getDB().getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO `classes` (`uuid`, `class`) VALUES (?, ?);");

            stmt.setString(1, player.getUniqueId().toString());
            stmt.setString(2, clazz.getName());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean hasPurchased(Player player, Class clazz) {

        try {
            Connection connection = Main.getDB().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `classes` WHERE `uuid` = ? AND `class` = ? LIMIT 1; ");

            stmt.setString(1, player.getUniqueId().toString());
            stmt.setString(2, clazz.getName());

            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return false;
    }
}

