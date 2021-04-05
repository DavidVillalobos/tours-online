/*
 * File: ControllerLikeTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.LikeTour;
import com.getyourtour.service.ServiceLikeTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerLikeTour {

    private final ServiceLikeTour service = new ServiceLikeTour();

    @GetMapping("/like")
    public LikeTour get(@RequestParam Integer id){
        try{
            return service.getLikeTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @GetMapping("/likes")
    public List<LikeTour> getAllLikes(){
        try{
            return service.getAllLikeTours();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @GetMapping("/likes/tour")
    public List<LikeTour> getLikeByTour(@RequestParam Integer id){
        try{
            return service.getLikeToursByTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @GetMapping("/likes/user")
    public List<LikeTour> getLikeByUser(@RequestParam Integer id){
        try{
            return service.getLikeToursByUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @GetMapping("/like/tour/user")
    public Boolean getLikeTourByTourAndUser(@RequestParam Integer id_tour, @RequestParam Integer id_user){
        try{
            return service.getLikeTour(id_tour, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @PostMapping("/like")
    public int addLikeTour(@RequestBody LikeTour likeTour){
        try{
            return service.addLikeTour(likeTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like cannot be added", e);
        }
    }

    @DeleteMapping("/like")
    public int deleteLikeTour(@RequestParam Integer id){
        try{
            return service.deleteLikeTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @DeleteMapping("/like/tour/user")
    public int deleteLikeTourByTourAndUser(@RequestParam Integer id_tour, @RequestParam Integer id_user){
        try{
            return service.deleteLikeTour(id_tour, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

}