/*
 * File: ServiceTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoTour;
import com.getyourtour.model.Tour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

public class ServiceTour {

    private final DaoTour dao_tour = new DaoTour();

    public Tour getTour(Integer id) throws Exception {
        return dao_tour.get(id, false);
    }

    public List<Tour> getAllTours() throws Exception {
        return dao_tour.get();
    }

    public List<Tour> getFilterTours(String place, String departure, String arrival) throws Exception {
        if(place.isEmpty()) place = "default";
        if(arrival.isEmpty()) arrival = "default";
        if(departure.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            departure =  sdf.format(new Date());
        }
        return dao_tour.getFilterTours(place, departure, arrival);
    }

    public int addTour(Tour tour) throws Exception {
        if(tour.getName().isEmpty()){
            throw new Exception("The Name of tour is required");
        }
        return dao_tour.post(tour);
    }

    public int updateTour(Tour tour) throws Exception {
        return dao_tour.put(tour);
    }

    public int deleteTour(Integer id) throws Exception {
        return dao_tour.delete(id);
    }

}
