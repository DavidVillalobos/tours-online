/*
 * File: Controller.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String index() {
        return "RestFul Api of App GetYourTours";
    }

    @GetMapping("/about")
    public String about() {
        return "Get Your Tours :D \n Backend Build with Java, Spring boot and Gradle";
    }

    @GetMapping("/author")
    public String author() {
        return "{\"author\":\"Luis David Villalobos Gonzalez\",\"github\":\"https://github.com/DavidVillalobos\"}";
    }
 
}