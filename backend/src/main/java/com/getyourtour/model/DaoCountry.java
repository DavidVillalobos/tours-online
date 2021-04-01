/*
 * File: DaoCountry.java
 * author: David Villalobos
 * Date: 2021/03/31
 */

package com.getyourtour.model;

import com.getyourtour.logic.Country;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCountry{

    private Connection_DB db;
   
    public DaoCountry(){
        db = Connection_DB.instance();
    } 

    public Country get(int id){
        try{
            String sql = "SELECT * FROM Country WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Error: GET/country/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<Country> get(){
        List<Country> countries = new ArrayList<Country>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Country");
            while (resultSet.next()) { // for each register
                countries.add(map(resultSet));
            }
            if(0 == countries.size()){
                System.out.println("Error: GET/countries Does not exist any country in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return countries;
    }

    public int post(Country c){
        try{
            String sql = "INSERT INTO Country(Name) VALUES('%s')";
            sql = String.format(sql, c.getName());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public int put(Country c){
        try{
            String sql="UPDATE Country SET Name='%s' WHERE Id=%d";
            sql=String.format(sql, c.getName(), c.getId());   
            int result = db.executeUpdate(sql); 
            if(result == 0){
                System.out.println("Error: PUT/country/{" + c.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public int delete(int Id){
        try{
            String sql="DELETE FROM Country WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Error: DELETE/country/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private Country map(ResultSet rs) throws Exception{
        int id = rs.getInt("Id");
        String name = rs.getString("Name");
        return new Country(id, name);
    }

}