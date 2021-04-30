/*
 * File: DaoCity.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.data;

import com.getyourtour.model.City;
import com.getyourtour.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCity{

    private final ConnectionDB db = ConnectionDB.instance();

    public DaoCity(){
    }

    public City get(Integer id) throws Exception {
        try{
            String sql = "SELECT * FROM City WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()) {
                return map(rs);
            }
            throw new SQLException("/city/?=" + id + " Does not exist in DataBase");
        } catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public List<City> get() throws Exception {
        List<City> cities = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from City");
            while (resultSet.next()) {
                cities.add(map(resultSet));
            }
            if(cities.size() == 0){
                throw new SQLException("/cities/ Does not exist any City in DataBase");
            }
            return cities;
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<City> getCitiesByCountry(Integer id_country) {
        List<City> cities = new ArrayList<>();
        try{
            String sql = "SELECT * from City where Id_Country = %d";
            sql = String.format(sql, id_country);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                cities.add(map(resultSet));
            }
            if(cities.size() == 0){
                throw new SQLException("/cities/country?id=" + id_country + " Does not exist any City in DataBase");
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return cities;
    }

    public Integer post(City c) throws Exception {
        String sql = "INSERT INTO City(Id_Country, Name) VALUES(%d, '%s')";
        sql = String.format(sql, c.getCountry().getId(), c.getName());
        return db.executeInsert(sql);
    }

    public Integer put(City c) throws Exception {
        try{
            String sql="UPDATE City SET Name='%s' WHERE Id=%d";
            sql=String.format(sql, c.getName(), c.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("/City/{" + c.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Integer delete(Integer Id) throws Exception {
        try{
            String sql="DELETE FROM City WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("city/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    private City map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        DaoCountry dc = new DaoCountry();
        Country country = dc.get(rs.getInt("Id_Country"), false);
        return new City(id, country, name);
    }

}