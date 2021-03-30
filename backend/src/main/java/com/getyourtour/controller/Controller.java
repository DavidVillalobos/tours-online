package com.getyourtour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {
    @GetMapping("/")
    public String index() {
        return "Get Your Tours Backend :D \n Build in Java with gradle and spring boot";
    }
    @GetMapping("/countries")
    public List<Country> getAllCountrys(){
        return new DaoModel().getAllCountries();
    }
}