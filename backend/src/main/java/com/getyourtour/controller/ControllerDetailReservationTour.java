/*
 * File: ControllerDetailReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.DetailReservationTour;
import com.getyourtour.service.ServiceDetailReservationTour;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerDetailReservationTour {

    private final ServiceDetailReservationTour service = new ServiceDetailReservationTour();

    @GetMapping("/details-reservation")
    public DetailReservationTour get(@RequestParam Integer id){
        return service.getDetail(id);
    }

    @GetMapping("/details-reservations")
    public List<DetailReservationTour> getAllDetailsReservation(){
        return service.getAllDetail();
    }

    @GetMapping("/details-reservation/reservation")
    public List<DetailReservationTour> getDetailsByReservation(@RequestParam Integer id){
        return service.getAllDetailByReservation(id);
    }

    @PostMapping("/details-reservation")
    public int addDetailReservationTour(@RequestBody DetailReservationTour detail){
        return service.addDetail(detail);
    }

    @PutMapping("/details-reservation")
    public int updateDetailReservationTour(@RequestBody DetailReservationTour detail){
        return service.updateDetail(detail);
    }

    @DeleteMapping("/details-reservation")
    public int deleteDetailReservationTour(@RequestParam Integer id){
        return service.deleteDetail(id);
    }

}