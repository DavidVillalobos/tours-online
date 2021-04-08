/*
 * File: CommentTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

public class CommentTour {

    private Integer id;
    private Tour tour;
    private User user;
    private float rating;
    private String description;

    public CommentTour() {
        this.id = 0;
        this.tour = null;
        this.user = null;
        this.rating = 0;
        this.description = "";
    }

    public CommentTour(Integer id, Tour tour, User user, float rating, String description) {
        this.id = id;
        this.tour = tour;
        this.user = user;
        this.rating = rating;
        this.description = description;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
