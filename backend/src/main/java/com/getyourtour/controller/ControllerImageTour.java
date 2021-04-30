/*
 * File: ControllerImageTour.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.ImageTour;
import com.getyourtour.model.Tour;
import com.getyourtour.service.ServiceImageTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ControllerImageTour {

    private final ServiceImageTour service = new ServiceImageTour();

    @GetMapping("/{id}")
    public ImageTour get(@PathVariable("id") Integer id){
        try{
            return service.getImageTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

    @GetMapping("/only-image/{id}")
    public byte[] getImage(@PathVariable("id") Integer id){
        try{
            return service.getOnlyImage(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

    @GetMapping("main-image/only-image/tour/{id_tour}")
    public byte[] getImageByTour(@PathVariable("id_tour") Integer id_tour){
        try{
            return service.getOnlyImageTour(id_tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

    public List<ImageTour> getAllImages(){
        try{
            return service.getAllImageTours();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Images not found", e);
        }
    }

    @GetMapping("/tours/{id_tour}")
    public List<ImageTour> getImagesByTour(@PathVariable("id_tour") Integer id_tour){
        try{
            return service.getImagesByTour(id_tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Images not found", e);
        }
    }

    public int addImageTour(@RequestBody ImageTour imageTour){
        try{
            return service.addImageTour(imageTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image cannot be added", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteImageTour(@PathVariable("id") Integer id){
        try{
            return service.deleteImageTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

    @DeleteMapping("/tours/{id_tour}")
    public int deleteImagesOfTour(@PathVariable("id_tour") Integer id_tour){
        try{
            return service.deleteImageTourByTour(id_tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

}