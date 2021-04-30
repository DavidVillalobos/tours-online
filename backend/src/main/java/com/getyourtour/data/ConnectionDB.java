/*
 * File ConnectionDB.java
 * author David Villalobos
 * Date 2021/04/03
 */
package com.getyourtour.data;

import java.sql.*;

public class ConnectionDB{
    private static final String server = "DESKTOP-UPQBQ3T\\\\SQLEXPRESS";
    private static final String port = "1433";
    private static final String model = "GYTModel";
    private static final String username = "root";
    private static final String password = "root";
    private final Connection connection;
    private static ConnectionDB _instance;

    public static ConnectionDB instance(){
        if(_instance == null){
            _instance = new ConnectionDB();
        }
        return _instance;
    }
    private ConnectionDB(){
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

    public int executeUpdate(String statement) throws Exception {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException e) {
            throw new Exception("Error SQLException: " + e.getMessage());
        }
    }

    public int executeInsert(String query) throws Exception {
        try {
            PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            return generatedKey;
        } catch (SQLException e) {
            throw new Exception("Error SQLException: " + e.getMessage());
        }
    }
    
    public ResultSet executeQuery(String statement) throws Exception {
        try {
            Statement stm = connection.createStatement();
            return stm.executeQuery(statement);
        } catch (SQLException e) {
            throw new Exception("Error SQLException: " + e.getMessage());
        }
    }

}