/*
 * File: ControllerUser.java
 * author: David Villalobos
 * Date: 2021/04/02
 */
package com.getyourtour.controller;

import com.getyourtour.model.User;
import com.getyourtour.service.ServiceUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerUser {

    private final ServiceUser service = new ServiceUser();

    @GetMapping("/user")
    public User get(@RequestParam Integer id){
        return service.getUser(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/user/authenticate")
    public User get(@RequestBody User user){
        return service.authenticate(user);
    }

    @PostMapping("/user")
    public int addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @PutMapping("/user")
    public int updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/user")
    public int deleteUser(@RequestParam Integer id){
        return service.deleteUser(id);
    }

}