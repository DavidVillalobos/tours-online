/*
 * File: ControllerImageTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.ImageTour;
import com.getyourtour.service.ServiceImageTour;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerImageTour {

    private final ServiceImageTour service = new ServiceImageTour();

    @GetMapping("/image")
    public ImageTour get(@RequestParam Integer id){
        return service.getImageTour(id);
    }

    @GetMapping("/only-image")
    public byte[] getImage(@RequestParam Integer id){
        return service.getOnlyImageTour(id);
    }

    @GetMapping("/images")
    public List<ImageTour> getAllImages(){
        return service.getAllImageTours();
    }

    @GetMapping("/images/tour")
    public List<ImageTour> getImagesByTour(@RequestParam Integer id){
        return service.getImagesByTour(id);
    }

    @PostMapping("/image")
    public int addImageTour(@RequestBody ImageTour imageTour){
        return service.addImageTour(imageTour);
    }

    @DeleteMapping("/image")
    public int deleteImageTour(@RequestParam Integer id){
        return service.deleteImageTour(id);
    }

    @DeleteMapping("/image/tour")
    public int deleteImageTourByTour(@RequestParam Integer id_tour){
        return service.deleteImageTourByTour(id_tour);
    }

}