/*
 * File: ControllerReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.ReservationTour;
import com.getyourtour.service.ServiceReservationTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerReservationTour {

    private final ServiceReservationTour service = new ServiceReservationTour();

    @GetMapping("/reservation")
    public ReservationTour get(@RequestParam Integer id){
        try{
            return service.getReservation(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found", e);
        }
    }

    @GetMapping("/reservations")
    public List<ReservationTour> getAllReservationTours() {
        try{
            return service.getAllReservations();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservations not found", e);
        }
    }

    @PostMapping("/reservation")
    public int addReservationTour(@RequestBody ReservationTour reservationTour){
        try{
            return service.addReservation(reservationTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation cannot be added", e);
        }
    }

    @PutMapping("/reservation")
    public int updateReservationTour(@RequestBody ReservationTour reservationTour){
        try{
            return service.updateReservation(reservationTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found", e);
        }
    }

    @DeleteMapping("/reservation")
    public int deleteReservationTour(@RequestParam Integer id){
        try{
            return service.deleteReservation(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found", e);
        }
    }

}