/*
 * File: GetYourTour.java
 * author: David Villalobos
 * Date: 2021/04/01
 */

package com.getyourtour.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class GetYourTour {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SpringApplication.run(GetYourTour.class, args);
    }

}
