/*
 * File: ControllerLikeTour.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.LikeTour;
import com.getyourtour.service.ServiceLikeTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class ControllerLikeTour {

    private final ServiceLikeTour service = new ServiceLikeTour();

    @GetMapping("/{id}")
    public LikeTour get(@PathVariable("id") Integer id){
        try{
            return service.getLikeTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    public List<LikeTour> getAllLikes(){
        try{
            return service.getAllLikeTours();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Likes not found", e);
        }
    }

    @GetMapping("/tours/{id}")
    public List<LikeTour> getLikeByTour(@PathVariable("id") Integer id){
        try{
            return service.getLikeToursByTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @GetMapping("/users/{id}")
    public List<LikeTour> getLikeByUser(@RequestParam Integer id){
        try{
            return service.getLikeToursByUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @GetMapping("/tours/{id_tour}/users/{id_user}")
    public Boolean getLikeTourByTourAndUser(@PathVariable("id_tour") Integer id_tour, @PathVariable("id_user") Integer id_user){
        try{
            return service.getLikeTour(id_tour, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    public int addLikeTour(@RequestBody LikeTour likeTour){
        try{
            return service.addLikeTour(likeTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like cannot be added", e);
        }
    }

    public int deleteLikeTour(@RequestParam Integer id){
        try{
            return service.deleteLikeTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

    @DeleteMapping("/tours/{id_tour}/users/{id_user}")
    public int deleteLikeByTourAndUser(@PathVariable("id_tour") Integer id_tour, @PathVariable("id_user") Integer id_user){
        try{
            return service.deleteLikeTour(id_tour, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found", e);
        }
    }

}