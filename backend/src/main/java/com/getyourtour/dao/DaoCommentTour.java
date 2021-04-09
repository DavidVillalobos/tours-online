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

    private final ConnectionDB db = ConnectionDB.instance();

    public DaoCommentTour(){
    }

    public CommentTour get(Integer id) throws Exception {
        try{
            String sql = "SELECT * FROM Comment_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }
            throw new Exception("/Comment_Tour/{" + id + "} Does not exist in DataBase");
        } catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public List<CommentTour> get() throws Exception {
        List<CommentTour> comments = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Comment_Tour");
            while (resultSet.next()) {
                comments.add(map(resultSet));
            }
            if(0 == comments.size()){
                throw new Exception("/comments Does not exist any Comment_Tour in DataBase");
            }
        } catch(Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
        return comments;
    }

    public List<CommentTour> getByTour(Integer id_tour) throws Exception {
        List<CommentTour> comments = new ArrayList<>();
        try{
            String sql = "SELECT * from Comment_Tour where Id_Tour = %d";
            sql = String.format(sql, id_tour);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                comments.add(map(resultSet));
            }
            if(0 == comments.size()){
                throw new Exception("/comments/{" + id_tour + "} Does not exist any Comment_Tour in DataBase");
            }
        } catch(Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
        return comments;
    }

    public List<CommentTour> getByUser(Integer id_user) throws Exception {
        List<CommentTour> comments = new ArrayList<>();
        try{
            String sql = "SELECT * from Comment_Tour where Id_User = %d";
            sql = String.format(sql, id_user);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                comments.add(map(resultSet));
            }
            if(0 == comments.size()){
                throw new Exception("Log: GET/comments/{" + id_user + "} Does not exist any Comment_Tour in DataBase");
            }
        } catch(Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
        return comments;
    }

    public Integer post(CommentTour c) throws Exception {
        String sql = "INSERT INTO Comment_Tour(Id_Tour, Id_User, Rating, Description) VALUES(%d,%d,%f,'%s')";
        sql = String.format(sql, c.getTour().getId(), c.getUser().getId(), c.getRating(), c.getDescription());
        return db.executeInsert(sql);
    }

    public Integer put(CommentTour c) throws Exception {
        try{
            String sql="UPDATE Comment_Tour SET Rating=%f, Description='%s' WHERE Id=%d";
            sql=String.format(sql, c.getRating(), c.getDescription(), c.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("/Comment_Tour/{" + c.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer delete(Integer Id) throws Exception {
        try{
            String sql="DELETE FROM Comment_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: DELETE/Comment_Tour/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer delete(Integer idTour, Integer idUser) throws Exception {
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
        return null;
    }

    private CommentTour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        float rating = rs.getFloat("Rating");
        String description = rs.getString("Description");
        Tour tour = new Tour();
        tour.setId(rs.getInt("Id_Tour"));
        DaoUser du = new DaoUser();
        User user = du.get(rs.getInt("Id_User"));
        return new CommentTour(id, tour, user, rating, description);
    }

}