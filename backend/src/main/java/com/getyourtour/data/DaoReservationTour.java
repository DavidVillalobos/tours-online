/*
 * File: DaoReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.data;

import com.getyourtour.model.DetailReservationTour;
import com.getyourtour.model.ReservationTour;
import com.getyourtour.model.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoReservationTour {

    private final ConnectionDB db = ConnectionDB.instance();

    public DaoReservationTour(){
    }

    public ReservationTour get(Integer id) throws Exception {
        try{
            String sql = "SELECT * FROM Reservation_Tour WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }
            throw new Exception("/reservation/{" + id + "} Does not exist in DataBase");
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<ReservationTour> get() throws Exception {
        List<ReservationTour> reservationTours = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from Reservation_Tour");
            while (resultSet.next()) {
                reservationTours.add(map(resultSet));
            }
            if(0 == reservationTours.size()){
                throw new Exception("Log: GET/reservations Does not exist any ReservationTour in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return reservationTours;
    }

    public Integer post(ReservationTour r) throws Exception {
        String sql = "INSERT INTO Reservation_Tour(Id_User, Commission, SubTotal, Total) VALUES (%d, %f, %f, %f)";
        sql = String.format(sql, r.getUser().getId(), r.getCommission(), r.getSubTotal(), r.getTotal());
        return db.executeInsert(sql);
    }

    public Integer put(ReservationTour r) throws Exception {
        try{
            String sql="UPDATE Reservation_Tour SET Commission=%f SubTotal=%f, Total=%f WHERE Id=%d";
            sql=String.format(sql, r.getCommission(), r.getSubTotal(), r.getTotal(), r.getId());
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: PUT/reservation/{" + r.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Integer delete(Integer Id) throws Exception {
        try{
            String sql="DELETE FROM Reservation_Tour WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("Log: DELETE/reservation/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    private ReservationTour map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        float subtotal = rs.getFloat("SubTotal");
        float total = rs.getFloat("Total");
        float commision = rs.getFloat("Commission");
        ReservationTour result = new ReservationTour(id, commision, subtotal, total);

        DaoUser dc = new DaoUser();
        DaoDetailReservationTour dd = new DaoDetailReservationTour();

        User user = dc.get(rs.getInt("Id_User"));
        List<DetailReservationTour> details = dd.getByReservationTour(id);

        result.setUser(user);
        result.setDetails(details);

        return result;
    }

}