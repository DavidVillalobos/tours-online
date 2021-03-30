package com.getyourtour;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// class must be SINGLETON

public class DataBase{
    private Connection connection;
    
    public DataBase(){
        // must be read properties from application.properties 
        String connectionUrl =
        "jdbc:sqlserver://DESKTOP-UPQBQ3T\\SQLEXPRESS:1433;"
        + "database=GYTModel;"
        + "user=root;"
        + "password=root;"
        + "trustServerCertificate=false;"
        + "loginTimeout=30;";
        try {
            connection = DriverManager.getConnection(connectionUrl);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
}