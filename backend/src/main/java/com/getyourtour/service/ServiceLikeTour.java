/*
 * File: ServiceLikeTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoLikeTour;
import com.getyourtour.model.LikeTour;

import java.util.List;

public class ServiceLikeTour {

    private final DaoLikeTour dao_likeTour = new DaoLikeTour();

    public LikeTour getLikeTour(Integer id){
        return dao_likeTour.get(id);
    }

    public List<LikeTour> getAllLikeTours(){
        return dao_likeTour.get();
    }

    public List<LikeTour> getLikeToursByTour(Integer id_tour){
        return dao_likeTour.getByTour(id_tour);
    }

    public List<LikeTour> getLikeToursByUser(Integer id_user){
        return dao_likeTour.getByUser(id_user);
    }

    public int addLikeTour(LikeTour likeTour){
        if(likeTour.getTour().getId() != null && likeTour.getUser().getId() != null){
            return -1;
        }
        return dao_likeTour.post(likeTour);
    }

    public int deleteLikeTour(Integer id){
        return dao_likeTour.delete(id);
    }

    public int deleteLikeTour(Integer id_tour, Integer id_user){
        return dao_likeTour.delete(id_tour, id_user);
    }

}
