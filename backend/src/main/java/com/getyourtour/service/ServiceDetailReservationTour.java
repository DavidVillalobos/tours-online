/*
 * File: ServiceTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoDetailReservationTour;
import com.getyourtour.model.DetailReservationTour;
import com.getyourtour.model.Tour;

import java.util.List;

public class ServiceDetailReservationTour {

    private final DaoDetailReservationTour dao_detail_reservation_tour = new DaoDetailReservationTour();

    public DetailReservationTour getDetail(Integer id){
        return dao_detail_reservation_tour.get(id);
    }

    public List<DetailReservationTour> getAllDetail(){
        return dao_detail_reservation_tour.get();
    }

    public List<DetailReservationTour> getAllDetailByReservation(Integer id_reservation){
        return dao_detail_reservation_tour.getByReservationTour(id_reservation);
    }

    public int addDetail(DetailReservationTour detail){
        if(detail.getTour().getId() != null){
            return -1;
        }
        return dao_detail_reservation_tour.post(detail);
    }

    public int updateDetail(DetailReservationTour detail){
        return dao_detail_reservation_tour.put(detail);
    }

    public int deleteDetail(Integer id){
        return dao_detail_reservation_tour.delete(id);
    }

}
