/*
 * File: DaoTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.dao;

import com.getyourtour.model.City;
import com.getyourtour.model.Tour;
import com.getyourtour.model.Country;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DaoTour {

    private ConnectionDB db;

    public DaoTour(){
        db = ConnectionDB.instance();
    }

    public Tour get(Integer id){
        try{
            String sql = "SELECT * FROM Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/Tour/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<Tour> get(){
        List<Tour> tours = new ArrayList<Tour>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Tour");
            while (resultSet.next()) {
                tours.add(map(resultSet));
            }
            if(0 == tours.size()){
                System.out.println("Log: GET/tours Does not exist any Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return tours;
    }

    public Integer post(Tour t){
        try{
            String sql = "INSERT INTO Tour(Id_City, Name, Category, Description, Quota, Duration, " +
            "Price, Rating, Includes, NotIncludes) VALUES(%d,'%s','%s','%s',%d,'%s',%f,%d,'%s','%s')";
            sql = String.format(sql, t.getCity().getId(), t.getName(), t.getCategory(), t.getDescription(),
                    t.getQuota(), t.getDuration().toString(), t.getPrice(), t.getRating(), t.getIncludes(), t.getNotIncludes());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer put(Tour t){
        try{
            String sql="UPDATE Tour SET Name='%s', Category='%s', Description='%s', Quota=%d, Duration='%s', " +
                    "Price=%f, Rating=%d, Includes='%s', NotIncludes='%s' WHERE Id=%d";
            sql=String.format(sql, t.getName(), t.getCategory(), t.getDescription(), t.getQuota(),
                    t.getDuration().toString(), t.getPrice(), t.getRating(),
                    t.getIncludes(), t.getNotIncludes(), t.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: PUT/Tour/{" + t.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/Tour/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private Tour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        String category = rs.getString("Category");
        String description = rs.getString("Description");
        Integer quota = rs.getInt("Quota");
        Time duration = rs.getTime("Duration");
        Float price = rs.getFloat("Price");
        Short rating = rs.getShort("Rating");
        String includes = rs.getString("Includes");
        String notIncludes = rs.getString("NotIncludes");
        DaoCity dc = new DaoCity();
        City city = dc.get(rs.getInt("Id_City"));
        return new Tour(id, city, name, category, description, quota, duration, price, rating, includes, notIncludes);
    }

}