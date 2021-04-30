/*
 * File: ControllerTour.java
 * author: David Villalobos
 * Date: 2021/04/29
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
@RequestMapping("/tours")
public class ControllerTour {

    private final ServiceTour service = new ServiceTour();

    @GetMapping("/{id_tour}/users/{id_user}")
    public Tour get(@PathVariable("id_tour") Integer id_tour, @PathVariable(value = "id_user", required = false) Integer id_user){
        try{
            return service.getTour(id_tour, id_user, false);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }

    @GetMapping("/{id_tour}/complete/users/{id_user}")
    public Tour getComplete(@PathVariable("id_tour") Integer id_tour, @PathVariable(value = "id_user", required = false) Integer id_user){
        try{
            return service.getTour(id_tour, id_user, true);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }


    @GetMapping("/{id_user}")
    public List<Tour> getAllTours(@PathVariable(value = "id_user", required = false) Integer id_user) {
        try{
            return service.getAllTours(id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tours not found", e);
        }
    }

    @GetMapping("/filter/places/{place}/departures/{departure}/arrivals/{arrival}/users/{id_user}")
    public List<Tour> getFilterTours(@PathVariable(name = "place", required = false) String place,
                                     @PathVariable(name = "departure", required = false) String departure,
                                     @PathVariable(name = "arrival", required = false) String arrival,
                                     @PathVariable(name = "id_user", required = false) Integer id_user){
        try{
            return service.getFilterTours(place, departure, arrival, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tours not found", e);
        }
    }

    public int addTour(@RequestBody Tour tour){
        try{
            return service.addTour(tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour cannot be added", e);
        }
    }

    public int updateTour(@RequestBody Tour tour){
        try{
            return service.updateTour(tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteTour(@PathVariable("id") Integer id){
        try{
            return service.deleteTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }

}