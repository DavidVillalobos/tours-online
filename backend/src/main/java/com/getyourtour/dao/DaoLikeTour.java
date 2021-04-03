/*
 * File: DaoLikeTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.dao;

import com.getyourtour.model.LikeTour;
import com.getyourtour.model.Tour;
import com.getyourtour.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoLikeTour {

    private ConnectionDB db;

    public DaoLikeTour(){
        db = ConnectionDB.instance();
    }

    public LikeTour get(Integer id){
        try{
            String sql = "SELECT * FROM Like_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/like/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<LikeTour> get(){
        List<LikeTour> likes = new ArrayList<LikeTour>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Like_Tour");
            while (resultSet.next()) {
                likes.add(map(resultSet));
            }
            if(0 == likes.size()){
                System.out.println("Log: GET/likes Does not exist any Like_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return likes;
    }

    public List<LikeTour> getByTour(Integer id_tour){
        List<LikeTour> likes = new ArrayList<LikeTour>();
        try{
            String sql = "SELECT * from Like_Tour where Id_Tour = %d";
            sql = String.format(sql, id_tour);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                likes.add(map(resultSet));
            }
            if(0 == likes.size()){
                System.out.println("Log: GET/likes/{" + id_tour + "} Does not exist any Like_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return likes;
    }

    public List<LikeTour> getByUser(Integer id_user){
        List<LikeTour> likes = new ArrayList<LikeTour>();
        try{
            String sql = "SELECT * from Like_Tour where Id_User = %d";
            sql = String.format(sql, id_user);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                likes.add(map(resultSet));
            }
            if(0 == likes.size()){
                System.out.println("Log: GET/likes/{" + id_user + "} Does not exist any Like_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return likes;
    }

    public Integer post(LikeTour c){
        try{
            String sql = "INSERT INTO Like_Tour(Id_User, Id_Tour) VALUES(%d,%d)";
            sql = String.format(sql, c.getUser().getId(), c.getTour().getId());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    // There is not sense in creating a put method, the like only has foreign keys

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM Like_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/like/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer idTour, Integer idUser){
        try{
            String sql="DELETE FROM Like_Tour WHERE Id_Tour=%d AND Id_User=%d";
            sql = String.format(sql, idTour, idUser);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/like/tour={" + idTour + "}&user={" + idUser + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private LikeTour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        Tour tour = new Tour();
        tour.setId(rs.getInt("Id_Tour"));
        User user = new User();
        user.setId(rs.getInt("Id_User"));
        return new LikeTour(id, user, tour);
    }

}