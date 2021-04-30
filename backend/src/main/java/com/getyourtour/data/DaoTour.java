/*
 * File: DaoTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.data;

import com.getyourtour.model.*;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DaoTour {

    private final ConnectionDB db = ConnectionDB.instance();

    public DaoTour(){
    }

    public Tour get(Integer id, Integer id_user, boolean complete) throws Exception {
        try{
            String sql = "SELECT * FROM Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                if(complete){
                    return map(rs, id_user);
                } else {
                    return mapSimple(rs, id_user);
                }
            }
            throw new Exception("/tour/{" + id + "} Does not exist in DataBase");
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<Tour> get(Integer id_user) throws Exception {
        List<Tour> tours = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Tour");
            while (resultSet.next()) {
                tours.add(mapSimple(resultSet, id_user));
            }
            if(0 == tours.size()){
                throw new Exception("/tours Does not exist any Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return tours;
    }

    public List<Tour> getFilterTours(String place, String departure, String arrival, Integer id_user) {
        List<Tour> tours = new ArrayList<>();
        try{
            if(!place.equals("default")) place = "'" + place + "'";
            if(!arrival.equals("default")) arrival = "'" + arrival + "'";
            String sql = "SELECT * FROM F_FilterTours(%s,'%s',%s)";
            sql = String.format(sql, place, departure, arrival);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                tours.add(mapSimple(rs, id_user));
            }
            if(0 == tours.size()){
                throw new Exception("/tours/filter/ Does not exist any Tour in DataBase with that filter");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return tours;
    }

    public Integer post(Tour t) throws Exception {
        Date dt = t.getDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        t.setDate(c.getTime());
        String sql = "INSERT INTO Tour(Id_City, Name, Category, Description, StartDate, Quota, Reviews, Duration, " +
        "Price, Rating, Includes, NotIncludes) VALUES(%d,'%s', '%s', '%s', '%s', %d, %d, '%s', %f, %f, '%s', '%s')";
        sql = String.format(sql, t.getCity().getId(), t.getName(), t.getCategory(), t.getDescription(), t.getStringDate(),
                t.getQuota(), 0, t.getDuration().toString(), t.getPrice(), t.getRating(), t.getIncludes(), t.getNotIncludes());
        return db.executeInsert(sql);
    }

    public Integer put(Tour t) throws Exception {
        try{
            String sql="UPDATE Tour SET Name='%s', Category='%s', Description='%s', StartDate='%s', Quota=%d, Reviews=%d, Duration='%s', " +
                    "Price=%f, Rating=%f, Includes='%s', NotIncludes='%s' WHERE Id=%d";
            sql=String.format(sql, t.getName(), t.getCategory(), t.getDescription(), t.getStringDate(),
                    t.getQuota(), t.getReviews(), t.getDuration().toString(), t.getPrice(), t.getRating(),
                    t.getIncludes(), t.getNotIncludes(), t.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: PUT/Tour/{" + t.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public void updateQuota(Tour t) {
        try{
            String sql="UPDATE Tour SET Quota=%d WHERE Id=%d";
            sql=String.format(sql, t.getQuota(), t.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: PUT/Tour/Quota/{" + t.getId() + "} Does not exist in DataBase");
            }
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public Integer delete(Integer Id) throws Exception {
        try{
            String sql="DELETE FROM Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: DELETE/Tour/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    private Tour map(ResultSet rs, Integer id_user) throws Exception{
        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        String category = rs.getString("Category");
        String description = rs.getString("Description");
        Date date = rs.getDate("StartDate");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 0);
        date = c.getTime();
        Integer quota = rs.getInt("Quota");
        Integer reviews = rs.getInt("Reviews");
        Time duration = rs.getTime("Duration");
        float price = rs.getFloat("Price");
        float rating = rs.getFloat("Rating");
        String includes = rs.getString("Includes");
        String notIncludes = rs.getString("NotIncludes");

        Tour result = new Tour(id, name, category, description, date, quota, reviews, duration, price, rating, includes, notIncludes);

        DaoLikeTour dl = new DaoLikeTour();
        if(id_user != 0) {
            Boolean liked = dl.getByTour(id, id_user);
            result.setLiked(liked);
        }

        DaoCity dc = new DaoCity();
        DaoCommentTour dct = new DaoCommentTour();
        DaoImageTour di = new DaoImageTour();

        City city = dc.get(rs.getInt("Id_City"));
        List<LikeTour> likes = dl.getByTour(id);
        List<CommentTour> comments = dct.getByTour(id);
        List<ImageTour> images = di.getByTour(id);

        result.setCity(city);
        result.setLikes(likes);
        result.setComments(comments);
        result.setImages(images);
        return result;
    }

    private Tour mapSimple(ResultSet rs, Integer id_user)  throws Exception{
        Integer id = rs.getInt("Id");
        String name = rs.getString("Name");
        Date date = rs.getDate("StartDate");
        Integer quota = rs.getInt("Quota");
        Integer reviews = rs.getInt("Reviews");
        Time duration = rs.getTime("Duration");
        float price = rs.getFloat("Price");
        float rating = rs.getFloat("Rating");

        Tour result = new Tour(id, name, "", "", date, quota, reviews, duration, price, rating, "", "");
        if(id_user != 0) {
            DaoLikeTour dlt = new DaoLikeTour();
            Boolean liked = dlt.getByTour(id, id_user);
            result.setLiked(liked);
        }

        DaoCity dc = new DaoCity();
        DaoImageTour di = new DaoImageTour();

        City city = dc.get(rs.getInt("Id_City"));
        List<ImageTour> images = new ArrayList<>();

        images.add(di.getMainTourImage(id));
        result.setCity(city);
        result.setImages(images);
        return result;
    }

}