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

    public ImageTour() {
        this.id = 0;
        this.tour = null;
    }

    public ImageTour(Integer id, Tour tour, byte[] photo) {
        this.id = id;
        this.tour = tour;
        this.photo = photo;
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
}
