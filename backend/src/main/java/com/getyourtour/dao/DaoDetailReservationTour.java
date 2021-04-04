/*
 * File: DaoDetailReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.dao;

import com.getyourtour.model.DetailReservationTour;
import com.getyourtour.model.Tour;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoDetailReservationTour {

    private ConnectionDB db;

    public DaoDetailReservationTour(){
        db = ConnectionDB.instance();
    }

    public DetailReservationTour get(Integer id){
        try{
            String sql = "SELECT * FROM Detail_Reservation_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/detail-reservation/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<DetailReservationTour> get(){
        List<DetailReservationTour> details = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Detail_Reservation_Tour");
            while (resultSet.next()) {
                details.add(map(resultSet));
            }
            if(0 == details.size()){
                System.out.println("Log: GET/details-reservations Does not exist any DetailReservationTour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return details;
    }

    public List<DetailReservationTour> getByReservationTour(Integer idReservationTour){
        List<DetailReservationTour> details = new ArrayList<>();
        try{
            String sql = "SELECT * from Detail_Reservation_Tour where Id_Reservation_Tour = %d";
            sql = String.format(sql, idReservationTour);
            ResultSet resultSet = db.executeQuery(sql);
            while (resultSet.next()) {
                details.add(map(resultSet));
            }
            if(0 == details.size()){
                System.out.println("Log: GET/details-reservations/{" + idReservationTour + "} Does not exist any Like_Tour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return details;
    }

    public Integer post(DetailReservationTour d){
        try{
            String sql = "INSERT INTO Detail_Reservation_Tour(Id_Reservation_Tour, Id_Tour, Tickets)"
            + " VALUES(%d, %d, %d)";
            sql = String.format(sql, d.getReservationTour().getId(), d.getTour().getId(), d.getTickets());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer put(DetailReservationTour d){
        try{
            String sql="UPDATE Detail_Reservation_Tour SET Tickets=%d WHERE Id=%d";
            sql = String.format(sql, d.getTickets(), d.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: PUT/detail-reservation/{" + d.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM Detail_Reservation_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/detail-reservation/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private DetailReservationTour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        Integer tickets = rs.getInt("Tickets");
        DaoTour dc = new DaoTour();
        Tour tour = dc.get(rs.getInt("Id_Tour"), true);
        return new DetailReservationTour(id, null,tour, tickets);
    }

}