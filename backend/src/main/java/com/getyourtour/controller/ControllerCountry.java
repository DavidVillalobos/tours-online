/*
 * File: Controller.java
 * author: David Villalobos
 * Date: 2021/04/01
 */
package com.getyourtour.controller;

import com.getyourtour.model.*;

import com.getyourtour.service.ServiceCountry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerCountry {

    private final ServiceCountry service = new ServiceCountry();

    @GetMapping("/country")
    public Country get(@RequestParam Integer id, @RequestParam(defaultValue = "false") Boolean complete){
        try{
            return service.getCountry(id, complete);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found", e);
        }
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries(@RequestParam(defaultValue = "false") Boolean complete){
        try{
            return service.getAllCountries(complete);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Countries not found", e);
        }
    }

    @PostMapping("/country")
    public int addCountry(@RequestBody Country country){
        try{
            return service.addCountry(country);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country cannot be added", e);
        }
    }

    @PutMapping("/country")
    public int updateCountry(@RequestBody Country country){
        try{
            return service.updateCountry(country);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found", e);
        }
    }

    @DeleteMapping("/country")
    public int deleteCountry(@RequestParam Integer id){
        try{
            return service.deleteCountry(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found", e);
        }
    }

}