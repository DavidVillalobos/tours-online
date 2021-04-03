/*
 * File: ControllerReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.ReservationTour;
import com.getyourtour.service.ServiceReservationTour;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerReservationTour {

    private final ServiceReservationTour service = new ServiceReservationTour();

    @GetMapping("/reservation")
    public ReservationTour get(@RequestParam Integer id){
        return service.getReservation(id);
    }

    @GetMapping("/reservations")
    public List<ReservationTour> getAllReservationTours() {
        return service.getAllReservations();
    }

    @PostMapping("/reservation")
    public int addReservationTour(@RequestBody ReservationTour reservationTour){
        return service.addReservation(reservationTour);
    }

    @PutMapping("/reservation")
    public int updateReservationTour(@RequestBody ReservationTour reservationTour){
        return service.updateReservation(reservationTour);
    }

    @DeleteMapping("/reservation")
    public int deleteReservationTour(@RequestParam Integer id){
        return service.deleteReservation(id);
    }

}