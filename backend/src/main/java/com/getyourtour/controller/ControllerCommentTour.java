/*
 * File: ControllerCommentTour.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.CommentTour;
import com.getyourtour.service.ServiceCommentTour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class ControllerCommentTour {

    private final ServiceCommentTour service = new ServiceCommentTour();

    @GetMapping("/{id}")
    public CommentTour get(@PathVariable("id") Integer id){
        try{
            return service.getCommentTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

    public List<CommentTour> getAllComments(){
        try{
            return service.getAllCommentTours();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments not found", e);
        }
    }

    @GetMapping("/tours/{id}")
    public List<CommentTour> getCommentByTour(@PathVariable("id") Integer id){
        try{
            return service.getCommentToursByTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments not found", e);
        }
    }

    @GetMapping("/users/{id}")
    public List<CommentTour> getCommentByUser(@PathVariable("id") Integer id){
        try{
            return service.getCommentToursByUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments not found", e);
        }
    }

    public int addCommentTour(@RequestBody CommentTour commentTour){
        try{
            return service.addCommentTour(commentTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment cannot be added", e);
        }
    }

    public int updateCommentTour(CommentTour commentTour){
        try{
            return service.updateCommentTour(commentTour);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteCommentTour(@PathVariable("id") Integer id){
        try{
            return service.deleteCommentTour(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

    @DeleteMapping("tours/{id_tour}/users/{id_tour}")
    public int deleteCommentTourByUser(@PathVariable("id_tour") Integer id_tour, @PathVariable("id_user") Integer id_user){
        try{
            return service.deleteCommentTourByUser(id_tour, id_user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found", e);
        }
    }

}