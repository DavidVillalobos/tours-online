/*
 * File: ControllerTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.Tour;
import com.getyourtour.service.ServiceTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ControllerTour {

    private final ServiceTour service = new ServiceTour();

    @GetMapping("/tour")
    public Tour get(@RequestParam Integer id){
        try{
            return service.getTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }

    @GetMapping("/tours")
    public List<Tour> getAllTours() {
        try{
            return service.getAllTours();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tours not found", e);
        }
    }

    @GetMapping("/tours/filter")
    public List<Tour> getFilterTours(@RequestParam(defaultValue = "") String place,
                                     @RequestParam(defaultValue = "") String departure,
                                     @RequestParam(defaultValue = "") String arrival){
        try{
            return service.getFilterTours(place, departure, arrival);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tours not found", e);
        }
    }

    @PostMapping("/tour")
    public int addTour(@RequestBody Tour tour){
        try{
            return service.addTour(tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour cannot be added", e);
        }
    }

    @PutMapping("/tour")
    public int updateTour(@RequestBody Tour tour){
        try{
            return service.updateTour(tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }

    @DeleteMapping("/tour")
    public int deleteTour(@RequestParam Integer id){
        try{
            return service.deleteTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }

}