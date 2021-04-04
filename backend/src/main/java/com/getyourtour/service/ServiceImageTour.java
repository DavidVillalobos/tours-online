/*
 * File: ServiceImageTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoImageTour;
import com.getyourtour.model.ImageTour;

import java.util.Arrays;
import java.util.List;

public class ServiceImageTour {

    private final DaoImageTour dao_image_Tour = new DaoImageTour();

    public ImageTour getImageTour(Integer id){
        return dao_image_Tour.get(id);
    }

    public byte[] getOnlyImageTour(Integer id_tour) {
        return dao_image_Tour.getOnlyImageByTour(id_tour);
    }

    public List<ImageTour> getAllImageTours(){
        return dao_image_Tour.get();
    }

    public List<ImageTour> getImagesByTour(Integer id_tour){
        return dao_image_Tour.getByTour(id_tour);
    }

    public int addImageTour(ImageTour imageTour){
        if(Arrays.toString(imageTour.getPhoto()).isEmpty()){
            return -1;
        }
        return dao_image_Tour.post(imageTour);
    }

    public int deleteImageTour(Integer id){
        return dao_image_Tour.delete(id);
    }

    public int deleteImageTourByTour(Integer id_tour){
        return dao_image_Tour.deleteByTour(id_tour);
    }

}
