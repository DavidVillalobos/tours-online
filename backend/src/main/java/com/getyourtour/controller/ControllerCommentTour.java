/*
 * File: ControllerCommentTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.CommentTour;
import com.getyourtour.service.ServiceCommentTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerCommentTour {

    private final ServiceCommentTour service = new ServiceCommentTour();

    @GetMapping("/comment")
    public CommentTour get(@RequestParam Integer id){
        try{
            return service.getCommentTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

    @GetMapping("/comments")
    public List<CommentTour> getAllComments(){
        try{
            return service.getAllCommentTours();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments not found", e);
        }
    }

    @GetMapping("/comments/tour")
    public List<CommentTour> getCommentByTour(@RequestParam Integer id){
        try{
            return service.getCommentToursByTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments not found", e);
        }
    }

    @GetMapping("/comments/user")
    public List<CommentTour> getCommentByUser(@RequestParam Integer id){
        try{
            return service.getCommentToursByUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments not found", e);
        }
    }

    @PostMapping("/comment")
    public int addCommentTour(@RequestBody CommentTour commentTour){
        try{
            return service.addCommentTour(commentTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment cannot be added", e);
        }
    }

    @PutMapping("/comment")
    public int updateCommentTour(CommentTour commentTour){
        try{
            return service.updateCommentTour(commentTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

    @DeleteMapping("/comment")
    public int deleteCommentTour(@RequestParam Integer id){
        try{
            return service.deleteCommentTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

    @DeleteMapping("/comment/user")
    public int deleteCommentTourByUser(@RequestParam Integer id_tour, @RequestParam Integer id_user){
        try{
            return service.deleteCommentTourByUser(id_tour, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

}