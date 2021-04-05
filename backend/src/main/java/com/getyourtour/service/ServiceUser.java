/*
 * File: ServiceUser.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoUser;
import com.getyourtour.model.User;

import java.util.List;

public class ServiceUser {

    private final DaoUser dao_user = new DaoUser();

    public User getUser(Integer id) throws Exception {
        return dao_user.get(id);
    }

    public List<User> getAllUsers() throws Exception {
        return dao_user.get();
    }

    public int addUser(User user) throws Exception {
        if(user.getEmail().isEmpty() || user.getPassword().isEmpty()){
            throw new Exception("The Email and Password is required");
        }
        return dao_user.post(user);
    }

    public User authenticate(User user) throws Exception {
        User user_database =  dao_user.getByEmail(user.getEmail());
        if(user_database != null){
            if(user_database.getPassword().equals(user.getPassword())){
                return user_database;
            }
            throw new Exception("The Email and Password do not match");
        }
        throw new Exception("The Email does not correspond to any user");
    }

    public int updateUser(User user) throws Exception {
        return dao_user.put(user);
    }

    public int deleteUser(Integer id) throws Exception {
        return dao_user.delete(id);
    }

}
