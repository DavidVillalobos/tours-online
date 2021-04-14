/*
 * File: ImageTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

public class ImageTour {
    private Integer id;
    private Tour tour;
    private byte[] photo; //       send  backend --json--> frontend
    private String photoBase64; // send frontend --json--> backend
    private Integer mainPhoto;

    public ImageTour() {
        this.id = 0;
        this.tour = null;
        this.mainPhoto = 0;
        this.photoBase64 = "";
    }

    public ImageTour(Integer id, Tour tour, byte[] photo, Integer mainPhoto) {
        this.id = id;
        this.tour = tour;
        this.photo = photo;
        this.mainPhoto = mainPhoto;
        this.photoBase64 = "";
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

    public Integer getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(Integer mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void getPhotoBase64(String photoBase64){
        this.photoBase64 = photoBase64;
    }

}
