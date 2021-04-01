/*
 * File: Controller.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerCountry {
 
    @GetMapping("/country")
    public Country get(@RequestParam int Id){
        return new DaoModel().getCountry(Id);
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        return new DaoModel().getAllCountries();
    }
    /*
        public Country getCountry(int Id){
        return new DaoModel().get(Id);
    }
    public List<Country> getAllCountries(){
        return dao_country.get();
    }
    public int addCountry(Country c){
        return dao_country.post(c);
    }
    public int updateCountry(Country c){
        return dao_country.put(c);
    }
    public int deleteCountry(int id){
        return dao_country.put(c);
    }
    */
}