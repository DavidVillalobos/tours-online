/*
 * File: DaoCountry.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.dao;

import com.getyourtour.model.Country;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCountry{

    private final ConnectionDB db = ConnectionDB.instance();
   
    public DaoCountry(){
    } 

    public Country get(Integer id) throws Exception {
        try{
            String sql = "SELECT * FROM Country WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }
            System.out.println("Log: GET/country/{" + id + "} Does not exist in DataBase");
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<Country> get() throws Exception {
        List<Country> countries = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Country");
            while (resultSet.next()) {
                countries.add(map(resultSet));
            }
            if(0 == countries.size()){
                throw new Exception("/countries Does not exist any country in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return countries;
    }

    public Integer post(Country c) throws Exception {
        String sql = "INSERT INTO Country(Name) VALUES('%s')";
        sql = String.format(sql, c.getName());
        return db.executeInsert(sql);
    }

    public Integer put(Country c) throws Exception {
        try{
            String sql="UPDATE Country SET Name='%s' WHERE Id=%d";
            sql=String.format(sql, c.getName(), c.getId());   
            int result = db.executeUpdate(sql); 
            if(result == 0){
                throw new Exception("/country/{" + c.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Integer delete(Integer Id) throws Exception {
        try{
            String sql="DELETE FROM Country WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: DELETE/country/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    private Country map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        return new Country(id, name);
    }

}