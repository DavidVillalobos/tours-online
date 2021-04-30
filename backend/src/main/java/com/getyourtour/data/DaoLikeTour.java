/*
 * File: DaoLikeTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.data;

import com.getyourtour.model.LikeTour;
import com.getyourtour.model.Tour;
import com.getyourtour.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoLikeTour {

    private final ConnectionDB db = ConnectionDB.instance();

    public DaoLikeTour(){
    }

    public LikeTour get(Integer id) throws Exception {
        try{
            String sql = "SELECT * FROM Like_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }
            throw new Exception("Log: GET/like/{" + id + "} Does not exist in DataBase");
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<LikeTour> get() throws Exception {
        List<LikeTour> likes = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Like_Tour");
            while (resultSet.next()) {
                likes.add(map(resultSet));
            }
            if(0 == likes.size()){
                throw new Exception("/likes Does not exist any Like_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return likes;
    }

    public List<LikeTour> getByTour(Integer id_tour){
        List<LikeTour> likes = new ArrayList<>();
        try{
            String sql = "SELECT * from Like_Tour where Id_Tour = %d";
            sql = String.format(sql, id_tour);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                likes.add(map(resultSet));
            }
            if(0 == likes.size()){
                throw new Exception("/likes/{" + id_tour + "} Does not exist any Like_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return likes;
    }

    public List<LikeTour> getByUser(Integer id_user){
        List<LikeTour> likes = new ArrayList<>();
        try{
            String sql = "SELECT * from Like_Tour where Id_User = %d";
            sql = String.format(sql, id_user);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                likes.add(map(resultSet));
            }
            if(0 == likes.size()){
                throw new Exception("/likes/{" + id_user + "} Does not exist any Like_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return likes;
    }

    public Boolean getByTour(Integer id_tour, Integer id_user) {
        try{
            String sql = "SELECT * FROM Like_Tour WHERE Id_Tour = %d AND Id_User = %d";
            sql = String.format(sql, id_tour, id_user);
            ResultSet rs = db.executeQuery(sql);
            return rs.next();
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Integer post(LikeTour c) throws Exception {
        String sql = "INSERT INTO Like_Tour(Id_User, Id_Tour) VALUES(%d,%d)";
        sql = String.format(sql, c.getUser().getId(), c.getTour().getId());
        return db.executeInsert(sql);
    }

    // There is not sense in creating a put method, the like only has foreign keys

    public Integer delete(Integer Id) throws Exception {
        try{
            String sql="DELETE FROM Like_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("/like/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Integer delete(Integer id_tour, Integer id_user) throws Exception {
        try{
            String sql="DELETE FROM Like_Tour WHERE Id_Tour=%d AND Id_User=%d";
            sql = String.format(sql, id_tour, id_user);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: DELETE/likes/tours/{" + id_tour + "}/users/{" + id_user + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
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