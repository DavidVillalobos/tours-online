/*
 * File: ControllerCity.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.City;
import com.getyourtour.service.ServiceCity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class ControllerCity {

    private final ServiceCity service = new ServiceCity();

    @GetMapping("/{id}")
    public City get(@PathVariable("id") Integer id){
        try{
            return service.getCity(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
        }
    }

    public List<City> getAllCities(){
        try{
            return service.getAllCities();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cities not found", e);
        }
    }

    @GetMapping("/country/{id}")
    public List<City> getCitiesByCountry(@PathVariable("id") Integer id){
        try{
            return service.getCitiesByCountry(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cities not found", e);
        }
    }

    public int addCity(@RequestBody City city){
        try{
            return service.addCity(city);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City cannot be added", e);
        }
    }

    public int updateCity(@RequestBody City city){
        try{
            return service.updateCity(city);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteCity(@PathVariable("id") Integer id){
        try{
            return service.deleteCity(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
        }
    }

}