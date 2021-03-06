/*
 * File: ServiceTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.data.DaoDetailReservationTour;
import com.getyourtour.model.DetailReservationTour;

import java.util.List;

public class ServiceDetailReservationTour {

    private final DaoDetailReservationTour dao_detail_reservation_tour = new DaoDetailReservationTour();

    public DetailReservationTour getDetail(Integer id) throws Exception {
        return dao_detail_reservation_tour.get(id);
    }

    public List<DetailReservationTour> getAllDetail() throws Exception {
        return dao_detail_reservation_tour.get();
    }

    public List<DetailReservationTour> getAllDetailByReservation(Integer id_reservation) throws Exception {
        return dao_detail_reservation_tour.getByReservationTour(id_reservation);
    }

    public int addDetail(DetailReservationTour detail) throws Exception {
        if(detail.getTour().getId() != null){
            throw new Exception("The id of user required");
        }
        return dao_detail_reservation_tour.post(detail);
    }

    public int updateDetail(DetailReservationTour detail) throws Exception {
        return dao_detail_reservation_tour.put(detail);
    }

    public int deleteDetail(Integer id) throws Exception {
        return dao_detail_reservation_tour.delete(id);
    }

}
