/*
 * File: ControllerDetailReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.DetailReservationTour;
import com.getyourtour.service.ServiceDetailReservationTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/detail-reservations")
public class ControllerDetailReservationTour {

    private final ServiceDetailReservationTour service = new ServiceDetailReservationTour();

    @GetMapping("/{id}")
    public DetailReservationTour get(@PathVariable("id") Integer id){
        try{
            return service.getDetail(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail Reservation not found", e);
        }
    }

    @GetMapping("")
    public List<DetailReservationTour> getAllDetailsReservation(){
        try{
            return service.getAllDetail();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Details Reservations not found", e);
        }
    }

    @GetMapping("/reservations/{id}")
    public List<DetailReservationTour> getDetailsByReservation(@PathVariable("id") Integer id){
        try{
            return service.getAllDetailByReservation(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Details Reservation not found", e);
        }
    }

    @PostMapping("")
    public int addDetailReservationTour(@RequestBody DetailReservationTour detail){
        try{
            return service.addDetail(detail);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail Reservation cannot be added", e);
        }
    }

    @PutMapping("")
    public int updateDetailReservationTour(@RequestBody DetailReservationTour detail){
        try{
            return service.updateDetail(detail);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail Reservation not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteDetailReservationTour(@PathVariable("id") Integer id){
        try{
            return service.deleteDetail(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail Reservation not found", e);
        }
    }

}