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

    public LikeTour getLikeTour(Integer id) throws Exception {
        return dao_likeTour.get(id);
    }

    public List<LikeTour> getAllLikeTours() throws Exception {
        return dao_likeTour.get();
    }

    public List<LikeTour> getLikeToursByTour(Integer id_tour) throws Exception {
        return dao_likeTour.getByTour(id_tour);
    }

    public List<LikeTour> getLikeToursByUser(Integer id_user) throws Exception {
        return dao_likeTour.getByUser(id_user);
    }

    public Boolean getLikeTour(Integer id_tour, Integer id_user) throws Exception {
        return dao_likeTour.getByTour(id_tour, id_user);
    }

    public int addLikeTour(LikeTour likeTour) throws Exception {
        if(likeTour.getTour().getId() != null && likeTour.getUser().getId() != null){
            throw new Exception("The Id of tour and user is required");
        }
        return dao_likeTour.post(likeTour);
    }

    public int deleteLikeTour(Integer id) throws Exception {
        return dao_likeTour.delete(id);
    }

    public int deleteLikeTour(Integer id_tour, Integer id_user) throws Exception {
        return dao_likeTour.delete(id_tour, id_user);
    }

}
