/*
 * File: ServiceTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoReservationTour;
import com.getyourtour.model.ReservationTour;

import java.util.List;

public class ServiceReservationTour {

    private DaoReservationTour dao_reservation_tour = new DaoReservationTour();

    public ReservationTour getReservation(Integer id){
        return dao_reservation_tour.get(id);
    }

    public List<ReservationTour> getAllReservation(){
        return dao_reservation_tour.get();
    }

    public int addReservation(ReservationTour reservation){
        if(reservation.getUser() != null){
            return -1;
        }
        return dao_reservation_tour.post(reservation);
    }

    public int updateReservation(ReservationTour reservation){
        return dao_reservation_tour.put(reservation);
    }

    public int deleteReservation(Integer id){
        return dao_reservation_tour.delete(id);
    }

}
