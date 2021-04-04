/*
 * File: ImageTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

public class ImageTour {
    private Integer id;
    private Tour tour;
    private byte[] photo;
    private Boolean mainPhoto;

    public ImageTour() {
        this.id = 0;
        this.tour = null;
        this.mainPhoto = false;
    }

    public ImageTour(Integer id, Tour tour, byte[] photo, Boolean mainPhoto) {
        this.id = id;
        this.tour = tour;
        this.photo = photo;
        this.mainPhoto = mainPhoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Boolean getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(Boolean mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

}
