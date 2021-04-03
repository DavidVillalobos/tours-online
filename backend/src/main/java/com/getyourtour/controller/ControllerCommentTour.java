/*
 * File: ControllerCommentTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.CommentTour;
import com.getyourtour.service.ServiceCommentTour;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerCommentTour {

    private final ServiceCommentTour service = new ServiceCommentTour();

    @GetMapping("/comment")
    public CommentTour get(@RequestParam Integer id){
        return service.getCommentTour(id);
    }

    @GetMapping("/comments")
    public List<CommentTour> getAllComments(){
        return service.getAllCommentTours();
    }

    @GetMapping("/comments/tour")
    public List<CommentTour> getCommentByTour(@RequestParam Integer id){
        return service.getCommentToursByTour(id);
    }

    @GetMapping("/comments/user")
    public List<CommentTour> getCommentByUser(@RequestParam Integer id){
        return service.getCommentToursByUser(id);
    }

    @PostMapping("/comment")
    public int addCommentTour(@RequestBody CommentTour commentTour){
        return service.addCommentTour(commentTour);
    }

    @PutMapping("/comment")
    public int updateCommentTour(CommentTour c){
        return service.updateCommentTour(c);
    }

    @DeleteMapping("/comment")
    public int deleteCommentTour(@RequestParam Integer id){
        return service.deleteCommentTour(id);
    }

    @DeleteMapping("/comment/user")
    public int deleteCommentTourByUser(@RequestParam Integer id_tour, @RequestParam Integer id_user){
        return service.deleteCommentTourByUser(id_tour, id_user);
    }

}