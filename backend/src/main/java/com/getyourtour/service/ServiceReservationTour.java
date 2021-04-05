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

    private final DaoReservationTour dao_reservation_tour = new DaoReservationTour();

    public ReservationTour getReservation(Integer id) throws Exception {
        return dao_reservation_tour.get(id);
    }

    public List<ReservationTour> getAllReservations() throws Exception {
        return dao_reservation_tour.get();
    }

    public int addReservation(ReservationTour reservation) throws Exception {
        if(reservation.getUser() != null){
            throw new Exception("The User is required");
        }
        return dao_reservation_tour.post(reservation);
    }

    public int updateReservation(ReservationTour reservation) throws Exception {
        return dao_reservation_tour.put(reservation);
    }

    public int deleteReservation(Integer id) throws Exception {
        return dao_reservation_tour.delete(id);
    }

}
