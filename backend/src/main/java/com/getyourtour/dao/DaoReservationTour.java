/*
 * File: DaoReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.dao;

import com.getyourtour.model.DetailReservationTour;
import com.getyourtour.model.ReservationTour;
import com.getyourtour.model.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoReservationTour {

    private ConnectionDB db;

    public DaoReservationTour(){
        db = ConnectionDB.instance();
    }

    public ReservationTour get(Integer id){
        try{
            String sql = "SELECT * FROM Reservation_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/reservation/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<ReservationTour> get(){
        List<ReservationTour> reservationTours = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Reservation_Tour");
            while (resultSet.next()) {
                reservationTours.add(map(resultSet));
            }
            if(0 == reservationTours.size()){
                System.out.println("Log: GET/reservations Does not exist any ReservationTour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return reservationTours;
    }

    public Integer post(ReservationTour r){
        try{
            String sql = "INSERT INTO Reservation_Tour(Id_User, SubTotal, Total)"
            + " VALUES(%d, %f, %f)";
            sql = String.format(sql,r.getUser().getId(), r.getSubTotal(), r.getTotal());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer put(ReservationTour r){
        try{
            String sql="UPDATE Reservation_Tour SET SubTotal=%f, Total=%f WHERE Id=%d";
            sql=String.format(sql, r.getSubTotal(), r.getTotal(), r.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: PUT/reservation/{" + r.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM Reservation_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/reservation/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private ReservationTour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        float subtotal = rs.getFloat("SubTotal");
        float total = rs.getFloat("Total");
        ReservationTour result = new ReservationTour(id, subtotal, total);

        DaoUser dc = new DaoUser();
        DaoDetailReservationTour dd = new DaoDetailReservationTour();

        User user = dc.get(rs.getInt("Id_User"));
        List<DetailReservationTour> details = dd.getByReservationTour(id);

        result.setUser(user);
        result.setDetails(details);

        return result;
    }

}