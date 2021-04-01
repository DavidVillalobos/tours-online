/*
 * File: Controller.java
 * author: David Villalobos
 * Date: 2021/04/01
 */
package com.getyourtour.controller;

import com.getyourtour.logic.*;
import com.getyourtour.model.*;

import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ControllerCountry {

    @GetMapping("/country")
    public Country get(@RequestParam Integer id){
        return new DaoModel().getCountry(id);
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        return new DaoModel().getAllCountries();
    }

    @PostMapping("/country")
    public int addCountry(@RequestBody Country country){
        return new DaoModel().addCountry(country);
    }

    @PutMapping("/country")
    public int updateCountry(@RequestBody Country country){
        return new DaoModel().updateCountry(country);
    }

    @DeleteMapping("/country")
    public int deleteCountry(@RequestParam Integer id){
        return new DaoModel().deleteCountry(id);
    }
}