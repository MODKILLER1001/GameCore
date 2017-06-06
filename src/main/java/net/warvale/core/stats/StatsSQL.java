package net.warvale.core.stats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class StatsSQL {
    private final String host, port, database, user, password;

    private Connection con;

    public StatsSQL(String host, String port, String database, String user, String password) {
    	this.host= host;
    	this.port= port;
        this.database= database;
        this.user= user;
        this.password= password;

        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true",user, password);
            ConsoleCommandSender console = Bukkit.getConsoleSender();
    		console.sendMessage(StatsUtil.color(" &aSTATS: DATABASE CONNECT SUCCESSFUL!"));
        } catch (SQLException e) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
    		console.sendMessage(StatsUtil.color(" &cSTATS: DATABASE CONNECT UNSUCCESSFUL! &4REASON: &e" + e.getMessage()));
        }
    }
    
    public void close() {
        try {
            if (con != null) {
                con.close();
                ConsoleCommandSender console = Bukkit.getConsoleSender();
        		console.sendMessage(StatsUtil.color(" STATS: &aThe connection to the SQL database has ended successfully!"));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String qry) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public void update(PreparedStatement statement) {
        try {
            statement.executeUpdate();    
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }finally{
           try {
             statement.close();
           } catch (SQLException e) {
             e.printStackTrace();
           }
    }
    }

    public boolean hasConnection() {
        return con != null;
    }

    public ResultSet query(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return null;
    }
}
