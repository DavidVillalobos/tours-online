/*
 * File: ControllerImageTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.ImageTour;
import com.getyourtour.service.ServiceImageTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerImageTour {

    private final ServiceImageTour service = new ServiceImageTour();

    @GetMapping("/image")
    public ImageTour get(@RequestParam Integer id){
        try{
            return service.getImageTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

    @GetMapping("/only-image")
    public byte[] getImage(@RequestParam Integer id){
        try{
            return service.getOnlyImageTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

    @GetMapping("/images")
    public List<ImageTour> getAllImages(){
        try{
            return service.getAllImageTours();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Images not found", e);
        }
    }

    @GetMapping("/images/tour")
    public List<ImageTour> getImagesByTour(@RequestParam Integer id){
        try{
            return service.getImagesByTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Images not found", e);
        }
    }

    @PostMapping("/image")
    public int addImageTour(@RequestBody ImageTour imageTour){
        try{
            return service.addImageTour(imageTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image cannot be added", e);
        }
    }

    @DeleteMapping("/image")
    public int deleteImageTour(@RequestParam Integer id){
        try{
            return service.deleteImageTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

    @DeleteMapping("/image/tour")
    public int deleteImageTourByTour(@RequestParam Integer id_tour){
        try{
            return service.deleteImageTourByTour(id_tour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
        }
    }

}