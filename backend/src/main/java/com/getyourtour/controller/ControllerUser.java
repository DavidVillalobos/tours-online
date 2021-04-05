/*
 * File: ControllerUser.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.User;
import com.getyourtour.service.ServiceUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerUser {

    private final ServiceUser service = new ServiceUser();

    @GetMapping("/user")
    public User get(@RequestParam Integer id){
        try{
            return service.getUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        try{
            return service.getAllUsers();
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found", e);
        }
    }

    @PostMapping("/user/authenticate")
    public User get(@RequestBody User user){
        try{
            return service.authenticate(user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e.getCause());
        }
    }

    @PostMapping("/user")
    public int addUser(@RequestBody User user){
        try{
            return service.addUser(user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User cannot be added", e);
        }
    }

    @PutMapping("/user")
    public int updateUser(@RequestBody User user){
        try{
            return service.updateUser(user);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @DeleteMapping("/user")
    public int deleteUser(@RequestParam Integer id){
        try{
            return service.deleteUser(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

}