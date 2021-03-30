package com.getyourtour;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCountry{
    private DataBase db;
   
    public DaoCountry(){
        db = new DataBase();
    } 

    public List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<Country>();
        try{

            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Country");
            while (resultSet.next()) {
                countries.add(new Country(resultSet.getInt("Id"), resultSet.getString("Name")));
            }
        } catch(SQLException e) {

        }
        return countries;
    }

}