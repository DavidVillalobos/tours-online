/*
 * File: Controller.java
 * author: David Villalobos
 * Date: 2021/04/01
 */
package com.getyourtour.controller;

import com.getyourtour.model.*;

import com.getyourtour.service.ServiceCountry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerCountry {

    private ServiceCountry service = new ServiceCountry();

    @GetMapping("/country")
    public Country get(@RequestParam Integer id){
        return service.getCountry(id);
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        return service.getAllCountries();
    }

    @PostMapping("/country")
    public int addCountry(@RequestBody Country country){
        return service.addCountry(country);
    }

    @PutMapping("/country")
    public int updateCountry(@RequestBody Country country){
        return service.updateCountry(country);
    }

    @DeleteMapping("/country")
    public int deleteCountry(@RequestParam Integer id){
        return service.deleteCountry(id);
    }
}