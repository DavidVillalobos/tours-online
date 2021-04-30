/*
 * File: ControllerReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.ReservationTour;
import com.getyourtour.service.ServiceReservationTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ControllerReservationTour {

    private final ServiceReservationTour service = new ServiceReservationTour();

    @GetMapping("/{id}")
    public ReservationTour get(@PathVariable("id") Integer id){
        try{
            return service.getReservation(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found", e);
        }
    }

    public List<ReservationTour> getAllReservationTours() {
        try{
            return service.getAllReservations();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservations not found", e);
        }
    }

    public int addReservationTour(@RequestBody ReservationTour reservationTour){
        try{
            return service.addReservation(reservationTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation cannot be added", e);
        }
    }

    public int updateReservationTour(@RequestBody ReservationTour reservationTour){
        try{
            return service.updateReservation(reservationTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteReservationTour(@PathVariable("id") Integer id){
        try{
            return service.deleteReservation(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found", e);
        }
    }

}