/*
 * File: ServiceImageTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoImageTour;
import com.getyourtour.model.ImageTour;

import java.util.List;

public class ServiceImageTour {

    private DaoImageTour dao_image_Tour = new DaoImageTour();

    public ImageTour getImageTour(Integer id){
        return dao_image_Tour.get(id);
    }

    public List<ImageTour> getAllImageTours(){
        return dao_image_Tour.get();
    }

    public List<ImageTour> getByTour(Integer id_tour){
        return dao_image_Tour.getByTour(id_tour);
    }

    public int addImageTour(ImageTour imageTour){
        if(imageTour.getPhoto().toString().isEmpty()){
            return -1;
        }
        return dao_image_Tour.post(imageTour);
    }

    public int deleteImageTour(Integer id){
        return dao_image_Tour.delete(id);
    }

    public int deleteImageTourByTour(Integer id_tour){
        return dao_image_Tour.delete(id_tour);
    }

}
