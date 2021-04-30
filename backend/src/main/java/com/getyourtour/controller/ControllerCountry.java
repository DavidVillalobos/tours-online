/*
 * File: Controller.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.*;

import com.getyourtour.service.ServiceCountry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class ControllerCountry {

    private final ServiceCountry service = new ServiceCountry();

    @GetMapping("/{id}")
    public Country get(@PathVariable("id") Integer id){
        try{
            return service.getCountry(id, false);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found", e);
        }
    }

    @GetMapping("/with-cities/{id}")
    public Country getWithCities(@PathVariable("id") Integer id){
        try{
            return service.getCountry(id, true);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found", e);
        }
    }

    public List<Country> getAllCountries(){
        try{
            return service.getAllCountries(true);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Countries not found", e);
        }
    }

    @GetMapping("/with-cities")
    public List<Country> getAllCountriesComplete(){
        try{
            return service.getAllCountries(true);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Countries not found", e);
        }
    }

    public int addCountry(@RequestBody Country country){
        try{
            return service.addCountry(country);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country cannot be added", e);
        }
    }

    public int updateCountry(@RequestBody Country country){
        try{
            return service.updateCountry(country);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteCountry(@PathVariable("id") Integer id){
        try{
            return service.deleteCountry(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found", e);
        }
    }

}