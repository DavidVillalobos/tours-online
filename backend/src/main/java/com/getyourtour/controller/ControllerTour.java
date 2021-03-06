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

    @GetMapping("/{id_tour}")
    public Tour get(@PathVariable("id_tour") Integer id_tour,
                    @RequestParam(name = "id_user", required = false, defaultValue = "0") Integer id_user,
                    @RequestParam(name = "complete", required = false, defaultValue = "true") Boolean complete){
        try{
            return service.getTour(id_tour, id_user, complete);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour not found", e);
        }
    }

    @GetMapping("")
    public List<Tour> getAllTours(@RequestParam(name = "id_user", required = false, defaultValue = "0") Integer id_user) {
        try{
            return service.getAllTours(id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tours not found", e);
        }
    }

    @GetMapping("/filter")
    public List<Tour> getFilterTours(@RequestParam(name = "place", required = false) String place,
                                     @RequestParam(name = "departure", required = false) String departure,
                                     @RequestParam(name = "arrival", required = false) String arrival,
                                     @RequestParam(name = "id_user", required = false) Integer id_user){
        try{
            return service.getFilterTours(place, departure, arrival, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tours not found", e);
        }
    }

    @PostMapping("")
    public int addTour(@RequestBody Tour tour){
        try{
            return service.addTour(tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tour cannot be added", e);
        }
    }

    @PutMapping("")
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