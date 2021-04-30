/*
 * File: ControllerUser.java
 * author: David Villalobos
 * Date: 2021/04/29
 */
package com.getyourtour.controller;

import com.getyourtour.model.User;
import com.getyourtour.service.ServiceUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ControllerUser {

    private final ServiceUser service = new ServiceUser();

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id){
        try{
            return service.getUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    public List<User> getAllUsers() {
        try{
            return service.getAllUsers();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found", e);
        }
    }

    @PostMapping("/authenticate")
    public User get(@RequestBody User user){
        try{
            return service.authenticate(user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e.getCause());
        }
    }

    public int addUser(@RequestBody User user){
        try{
            return service.addUser(user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User cannot be added", e);
        }
    }

    public int updateUser(@RequestBody User user){
        try{
            return service.updateUser(user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable("id") Integer id){
        try{
            return service.deleteUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

}