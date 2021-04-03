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

    public User getUser(Integer id){
        return dao_user.get(id);
    }

    public List<User> getAllUsers(){
        return dao_user.get();
    }

    public int addUser(User user){
        if(user.getEmail().isEmpty() || user.getPassword().isEmpty()){
            return -1;
        }
        return dao_user.post(user);
    }

    public User authenticate(User user) {
        User user_database =  dao_user.getByEmail(user.getEmail());
        if(user_database != null){
            if(user_database.getPassword().equals(user.getPassword())){
                return user_database;
            }
        }
        return null;
    }

    public int updateUser(User user){
        return dao_user.put(user);
    }

    public int deleteUser(Integer id){
        return dao_user.delete(id);
    }


}
