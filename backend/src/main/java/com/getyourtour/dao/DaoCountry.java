/*
 * File: DaoCountry.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.dao;

import com.getyourtour.model.Country;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCountry{

    private ConnectionDB db;
   
    public DaoCountry(){
        db = ConnectionDB.instance();
    } 

    public Country get(Integer id){
        try{
            String sql = "SELECT * FROM Country WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/country/{" + id + "} Does not exist in DataBase");
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
            while (resultSet.next()) {
                countries.add(map(resultSet));
            }
            if(0 == countries.size()){
                System.out.println("Log: GET/countries Does not exist any country in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return countries;
    }

    public Integer post(Country c){
        try{
            String sql = "INSERT INTO Country(Name) VALUES('%s')";
            sql = String.format(sql, c.getName());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer put(Country c){
        try{
            String sql="UPDATE Country SET Name='%s' WHERE Id=%d";
            sql=String.format(sql, c.getName(), c.getId());   
            int result = db.executeUpdate(sql); 
            if(result == 0){
                System.out.println("Log: PUT/country/{" + c.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM Country WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/country/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private Country map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        return new Country(id, name);
    }

}