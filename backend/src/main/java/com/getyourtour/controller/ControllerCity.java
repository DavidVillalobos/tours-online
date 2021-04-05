/*
 * File: ControllerCity.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.City;
import com.getyourtour.service.ServiceCity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerCity {

    private final ServiceCity service = new ServiceCity();

    @GetMapping("/city")
    public City get(@RequestParam Integer id){
        try{
            return service.getCity(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
        }
    }

    @GetMapping("/cities")
    public List<City> getAllCities(){
        try{
            return service.getAllCities();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Citys not found", e);
        }
    }

    @GetMapping("/cities/country")
    public List<City> getCitiesByCountry(@RequestParam Integer id){
        try{
            return service.getCitiesByCountry(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Citys not found", e);
        }
    }

    @PostMapping("/city")
    public int addCity(@RequestBody City city){
        try{
            return service.addCity(city);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City cannot be added", e);
        }
    }

    @PutMapping("/city")
    public int updateCity(@RequestBody City city){
        try{
            return service.updateCity(city);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
        }
    }

    @DeleteMapping("/city")
    public int deleteCity(@RequestParam Integer id){
        try{
            return service.deleteCity(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
        }
    }

}