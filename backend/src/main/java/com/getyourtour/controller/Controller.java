/*
 * File: Controller.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @GetMapping("/")
    public String index() {
        return "RestFul Api Get Your Tours Backend";
    }

    @GetMapping("/about")
    public String about() {
        return "Get Your Tours Backend :D \n Build in Java with gradle and spring boot";
    }
 
}