/*
 * File: ServiceTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoTour;
import com.getyourtour.model.Tour;

import java.util.List;

public class ServiceTour {

    private final DaoTour dao_tour = new DaoTour();

    public Tour getTour(Integer id){
        return dao_tour.get(id);
    }

    public List<Tour> getAllTours(){
        return dao_tour.get();
    }

    public int addTour(Tour tour){
        if(tour.getName().isEmpty()){
            return -1;
        }
        return dao_tour.post(tour);
    }

    public int updateTour(Tour tour){
        return dao_tour.put(tour);
    }

    public int deleteTour(Integer id){
        return dao_tour.delete(id);
    }

}
