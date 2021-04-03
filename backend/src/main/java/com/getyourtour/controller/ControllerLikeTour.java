/*
 * File: ControllerLikeTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.LikeTour;
import com.getyourtour.service.ServiceLikeTour;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerLikeTour {

    private final ServiceLikeTour service = new ServiceLikeTour();

    @GetMapping("/like")
    public LikeTour get(@RequestParam Integer id){
        return service.getLikeTour(id);
    }

    @GetMapping("/likes")
    public List<LikeTour> getAllLikes(){
        return service.getAllLikeTours();
    }

    @GetMapping("/likes/tour")
    public List<LikeTour> getLikeByTour(@RequestParam Integer id){
        return service.getLikeToursByTour(id);
    }

    @GetMapping("/likes/user")
    public List<LikeTour> getLikeByUser(@RequestParam Integer id){
        return service.getLikeToursByUser(id);
    }

    @PostMapping("/like")
    public int addLikeTour(@RequestBody LikeTour likeTour){
        return service.addLikeTour(likeTour);
    }

    @DeleteMapping("/like")
    public int deleteLikeTour(@RequestParam Integer id){
        return service.deleteLikeTour(id);
    }

    @DeleteMapping("/like/tour")
    public int deleteLikeTourByTour(@RequestParam Integer id_tour){
        return service.deleteLikeTour(id_tour);
    }

    @DeleteMapping("/like/user")
    public int deleteLikeTourByTourAndUser(@RequestParam Integer id_tour, @RequestParam Integer id_user){
        return service.deleteLikeTour(id_tour, id_user);
    }

}