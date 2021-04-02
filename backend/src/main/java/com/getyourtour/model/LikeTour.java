/*
 * File: User.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

public class LikeTour {
    private Integer id;
    private User user;
    private Tour tour;

    public LikeTour() {
        this.id = 0;
        this.user = null;
        this.tour = null;
    }

    public LikeTour(Integer id, User user, Tour tour) {
        this.id = id;
        this.user = user;
        this.tour = tour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
