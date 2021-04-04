/*
 * File: DaoCity.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.dao;

import com.getyourtour.model.City;
import com.getyourtour.model.Country;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCity{

    private ConnectionDB db;

    public DaoCity(){
        db = ConnectionDB.instance();
    }

    public City get(Integer id){
        try{
            String sql = "SELECT * FROM City WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/City/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<City> get(){
        List<City> cities = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from City");
            while (resultSet.next()) {
                cities.add(map(resultSet));
            }
            if(0 == cities.size()){
                System.out.println("Log: GET/cities Does not exist any City in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return cities;
    }

    public List<City> getCitiesByCountry(Integer id_country){
        List<City> cities = new ArrayList<>();
        try{
            String sql = "SELECT * from City where Id_Country = %d";
            sql = String.format(sql, id_country);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                cities.add(map(resultSet));
            }
            if(0 == cities.size()){
                System.out.println("Log: GET/cities/{" + id_country + "} Does not exist any City in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return cities;
    }

    public Integer post(City c){
        try{
            String sql = "INSERT INTO City(Id_Country, Name) VALUES(%d, '%s')";
            sql = String.format(sql, c.getCountry().getId(), c.getName());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer put(City c){
        try{
            String sql="UPDATE City SET Name='%s' WHERE Id=%d";
            sql=String.format(sql, c.getName(), c.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: PUT/City/{" + c.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM City WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/City/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private City map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        DaoCountry dc = new DaoCountry();
        Country country = dc.get(rs.getInt("Id"));
        return new City(id, country, name);
    }

}