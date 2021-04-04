/*
 * File: DaoCommentTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.dao;

import com.getyourtour.model.CommentTour;
import com.getyourtour.model.Tour;
import com.getyourtour.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCommentTour {

    private ConnectionDB db;

    public DaoCommentTour(){
        db = ConnectionDB.instance();
    }

    public CommentTour get(Integer id){
        try{
            String sql = "SELECT * FROM Comment_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/Comment_Tour/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<CommentTour> get(){
        List<CommentTour> comments = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Comment_Tour");
            while (resultSet.next()) {
                comments.add(map(resultSet));
            }
            if(0 == comments.size()){
                System.out.println("Log: GET/comments Does not exist any Comment_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return comments;
    }

    public List<CommentTour> getByTour(Integer id_tour){
        List<CommentTour> comments = new ArrayList<>();
        try{
            String sql = "SELECT * from Comment_Tour where Id_Tour = %d";
            sql = String.format(sql, id_tour);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                comments.add(map(resultSet));
            }
            if(0 == comments.size()){
                System.out.println("Log: GET/comments/{" + id_tour + "} Does not exist any Comment_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return comments;
    }

    public List<CommentTour> getByUser(Integer id_user){
        List<CommentTour> comments = new ArrayList<>();
        try{
            String sql = "SELECT * from Comment_Tour where Id_User = %d";
            sql = String.format(sql, id_user);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                comments.add(map(resultSet));
            }
            if(0 == comments.size()){
                System.out.println("Log: GET/comments/{" + id_user + "} Does not exist any Comment_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return comments;
    }

    public Integer post(CommentTour c){
        try{
            String sql = "INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES(%d,%d,%d,'%s')";
            sql = String.format(sql, c.getTour().getId(), c.getUser().getId(), c.getRating(), c.getDescription());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer put(CommentTour c){
        try{
            String sql="UPDATE Comment_Tour SET Rating=%d, Description='%s' WHERE Id=%d";
            sql=String.format(sql, c.getRating(), c.getDescription(), c.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: PUT/Comment_Tour/{" + c.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM Comment_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/Comment_Tour/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer idTour, Integer idUser){
        try{
            String sql="DELETE FROM Comment_Tour WHERE Id_Tour=%d AND Id_User=%d";
            sql = String.format(sql, idTour, idUser);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/Comment_Tour/tour={" + idTour + "}&user={" + idUser + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private CommentTour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        Short rating = rs.getShort("Rating");
        String description = rs.getString("Description");
        Tour tour = new Tour();
        tour.setId(rs.getInt("Id_Tour"));
        User user = new User();
        user.setId(rs.getInt("Id_User"));
        return new CommentTour(id, tour, user, rating, description);
    }

}