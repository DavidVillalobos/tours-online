/*
 * File: ServiceTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.data.DaoDetailReservationTour;
import com.getyourtour.data.DaoReservationTour;
import com.getyourtour.data.DaoTour;
import com.getyourtour.model.ReservationTour;
import com.getyourtour.model.Tour;

import java.util.List;

public class ServiceReservationTour {

    private final DaoReservationTour dao_reservation_tour = new DaoReservationTour();
    private final DaoDetailReservationTour dao_detail_reservation_tour = new DaoDetailReservationTour();
    private final DaoTour dao_tour = new DaoTour();

    public ReservationTour getReservation(Integer id) throws Exception {
        return dao_reservation_tour.get(id);
    }

    public List<ReservationTour> getAllReservations() throws Exception {
        return dao_reservation_tour.get();
    }

    public int addReservation(ReservationTour reservation) throws Exception {
        if(reservation.getUser() == null){
            throw new Exception("The User is required");
        }
        // Valid quota of tours
        for(var detail : reservation.getDetails()){
            Tour tour = dao_tour.get(detail.getTour().getId(), 0, true);
            if(tour.getQuota() < detail.getTickets()) {
                throw new Exception("There is not enough quota for the tour " + tour.getName());
            }
            detail.setTour(tour);
            tour.setQuota(tour.getQuota() - detail.getTickets());
        }
        int id = dao_reservation_tour.post(reservation);
        reservation.setId(id); // Update id
        for(var detail : reservation.getDetails()){
            detail.setReservationTour(reservation);
            dao_detail_reservation_tour.post(detail);
            dao_tour.updateQuota(detail.getTour());
        }
        String to = reservation.getUser().getEmail();
        EmailService.send(to, "Detalle de reservación #" + id, EmailService.generateBillEmail(reservation));
        return id;
    }

    public int updateReservation(ReservationTour reservation) throws Exception {
        return dao_reservation_tour.put(reservation);
    }

    public int deleteReservation(Integer id) throws Exception {
        return dao_reservation_tour.delete(id);
    }

}
