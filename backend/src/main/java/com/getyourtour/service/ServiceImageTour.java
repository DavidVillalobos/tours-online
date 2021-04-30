/*
 * File: ServiceImageTour.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.service;

import com.getyourtour.data.DaoImageTour;
import com.getyourtour.model.ImageTour;

import java.util.List;

public class ServiceImageTour {

    private final DaoImageTour dao_image_Tour = new DaoImageTour();

    public ImageTour getImageTour(Integer id) throws Exception {
        return dao_image_Tour.get(id);
    }

    public byte[] getOnlyImage(Integer id) throws Exception {
        return dao_image_Tour.getOnlyImage(id);
    }

    public byte[] getOnlyImageTour(Integer id_tour) throws Exception {
        return dao_image_Tour.getOnlyImageByTour(id_tour);
    }

    public List<ImageTour> getAllImageTours() throws Exception {
        return dao_image_Tour.get();
    }

    public List<ImageTour> getImagesByTour(Integer id_tour) throws Exception {
        return dao_image_Tour.getByTour(id_tour);
    }

    public int addImageTour(ImageTour imageTour) throws Exception {
        if(imageTour.getPhotoBase64().isEmpty()){
            throw new Exception("The Image is required");
        }
        return dao_image_Tour.post(imageTour);
    }

    public int deleteImageTour(Integer id) throws Exception {
        return dao_image_Tour.delete(id);
    }

    public int deleteImageTourByTour(Integer id_tour) throws Exception {
        return dao_image_Tour.deleteByTour(id_tour);
    }

}
