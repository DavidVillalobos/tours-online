/*
 * File: ControllerCity.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.City;
import com.getyourtour.service.ServiceCity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerCity {

    private final ServiceCity service = new ServiceCity();

    @GetMapping("/city")
    public City get(@RequestParam Integer id){
        return service.getCity(id);
    }

    @GetMapping("/cities")
    public List<City> getAllCities(){
        return service.getAllCities();
    }

    @GetMapping("/cities/country")
    public List<City> getCitiesByCountry(@RequestParam Integer id){
        return service.getCitiesByCountry(id);
    }

    @PostMapping("/city")
    public int addCity(@RequestBody City city){
        return service.addCity(city);
    }

    @PutMapping("/city")
    public int updateCity(@RequestBody City city){
        return service.updateCity(city);
    }

    @DeleteMapping("/city")
    public int deleteCity(@RequestParam Integer id){
        return service.deleteCity(id);
    }

}