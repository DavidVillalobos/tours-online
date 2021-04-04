/*
 * File: DaoImageTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.dao;

import com.getyourtour.model.ImageTour;
import com.getyourtour.model.Tour;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoImageTour {

    private ConnectionDB db;

    public DaoImageTour(){
        db = ConnectionDB.instance();
    }

    public ImageTour get(Integer id){
        try{
            String sql = "SELECT * FROM Image_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/image/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public byte[] getOnlyImageByTour(Integer id_tour) {
        try{
            String sql = "select Photo from Image_Tour where Id_Tour = %d";
            sql = String.format(sql, id_tour);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return rs.getBytes("Photo");
            }else{
                System.out.println("Log: GET/only-image/{" + id_tour + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<ImageTour> get(){
        List<ImageTour> images = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Image_Tour");
            while (resultSet.next()) {
                images.add(map(resultSet));
            }
            if(0 == images.size()){
                System.out.println("Log: GET/images Does not exist any Image_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return images;
    }

    public List<ImageTour> getByTour(Integer id_tour){
        List<ImageTour> images = new ArrayList<>();
        try{
            String sql = "SELECT * from Image_Tour where Id_Tour = %d";
            sql = String.format(sql, id_tour);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                images.add(map(resultSet));
            }
            if(0 == images.size()){
                System.out.println("Log: GET/images/{" + id_tour + "} Does not exist any Image_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return images;
    }

    public ImageTour getMainTourImage(Integer id_tour) {
        try{
            String sql = "SELECT * from Image_Tour where Id_Tour = %d AND MainPhoto = 1";
            sql = String.format(sql, id_tour);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/image/main/{" + id_tour + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Integer post(ImageTour c){
        try{
            String sql = "INSERT INTO Image_Tour(Id_Tour, Photo, MainPhoto) VALUES(%d,'%s', %b)";
            sql = String.format(sql, c.getTour().getId(), Arrays.toString(c.getPhoto()), c.getMainPhoto());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    // There is not sense in creating a put method, the Image only has foreign keys and Photo its best delete

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM Image_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/image/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer deleteByTour(Integer idTour){
        try{
            String sql="DELETE FROM Image_Tour WHERE Id_Tour=%d";
            sql = String.format(sql, idTour);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/image/tour={" + idTour + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private ImageTour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        byte[] photo = rs.getBytes("Photo");
        Boolean mainPhoto = rs.getBoolean("MainPhoto");
        Tour tour = new Tour();
        tour.setId(rs.getInt("Id_Tour"));
        return new ImageTour(id, tour, photo, mainPhoto);
    }

}