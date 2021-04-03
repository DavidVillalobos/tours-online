/*
 * File: ControllerTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.Tour;
import com.getyourtour.service.ServiceTour;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerTour {

    private final ServiceTour service = new ServiceTour();

    @GetMapping("/tour")
    public Tour get(@RequestParam Integer id){
        return service.getTour(id);
    }

    @GetMapping("/tours")
    public List<Tour> getAllTours() {
        return service.getAllTours();
    }

    @PostMapping("/tour")
    public int addTour(@RequestBody Tour tour){
        return service.addTour(tour);
    }

    @PutMapping("/tour")
    public int updateTour(@RequestBody Tour tour){
        return service.updateTour(tour);
    }

    @DeleteMapping("/tour")
    public int deleteTour(@RequestParam Integer id){
        return service.deleteTour(id);
    }

}