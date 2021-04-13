/*
 * File: ServiceTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoImageTour;
import com.getyourtour.dao.DaoTour;
import com.getyourtour.model.ImageTour;
import com.getyourtour.model.Tour;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServiceTour {

    private final DaoTour dao_tour = new DaoTour();
    private final DaoImageTour dao_image = new DaoImageTour();

    public Tour getTour(Integer id, Integer id_user, Boolean simpleTour) throws Exception {
        return dao_tour.get(id, id_user, simpleTour);
    }

    public List<Tour> getAllTours(Integer id_user) throws Exception {
        return dao_tour.get(id_user);
    }

    public List<Tour> getFilterTours(String place, String departure, String arrival, Integer id_user) throws Exception {
        if(place.isEmpty()) place = "default";
        if(arrival.isEmpty()) arrival = "default";
        if(departure.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            departure =  sdf.format(new Date());
        }
        return dao_tour.getFilterTours(place, departure, arrival, id_user);
    }

    public int addTour(Tour tour) throws Exception {
        if(tour.getName().isEmpty()){
            throw new Exception("The Name of tour is required");
        }
        int id = dao_tour.post(tour);
        for(ImageTour image : tour.getImages()){
            image.setId(id);
            dao_image.post(image);
        }
        return id;
    }

    public int updateTour(Tour tour) throws Exception {
        return dao_tour.put(tour);
    }

    public int deleteTour(Integer id) throws Exception {
        return dao_tour.delete(id);
    }

}
