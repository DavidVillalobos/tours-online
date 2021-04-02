/*
 * @File Connection_DB.java
 * @author David Villalobos
 * @Date 2021/03/31
 */
package com.getyourtour.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Connection_DB{

    private final String server = "DESKTOP-UPQBQ3T\\\\SQLEXPRESS";
    private final String port = "1433";
    private final String model = "GYTModel";
    private final String username = "root";
    private final String password = "root";
    private Connection connection;
    private static Connection_DB _instance;

    public static Connection_DB instance(){
        if(_instance == null){
            _instance = new Connection_DB();
        }
        return _instance;
    }
    private Connection_DB(){
        connection = getConnection();
    }

    private Connection getConnection(){
        try {
            String connectionUrl = "jdbc:sqlserver://" + server + ":" + port + ";" 
            + "database=" + model + ";"
            + "user=" + username + ";"
            + "password=" + password + ";"
            + "trustServerCertificate=false;"
            + "loginTimeout=30;";
            return DriverManager.getConnection(connectionUrl);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int executeUpdate(String statement) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException e) {
            System.out.println("Error SQLException: " + e.getMessage());
            return 0;
        }
    }
    
    public ResultSet executeQuery(String statement){
        try {
            Statement stm = connection.createStatement();
            return stm.executeQuery(statement);
        } catch (SQLException e) {
            System.out.println("Error SQLException: " + e.getMessage());
        }
        return null;
    }

}