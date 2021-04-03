/*
 * File: ReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

import java.util.List;

public class ReservationTour{
    private Integer id;
    private User user;
    private float subTotal;
    private float total;
    private List<DetailReservationTour> details;

    public ReservationTour() {
        this.id = 0;
        this.user = null;
        this.subTotal = 0;
        this.total = 0;
        this.details = null;
    }

    public ReservationTour(Integer id, float subTotal, float total) {
        this.id = id;
        this.subTotal = subTotal;
        this.total = total;
        this.user = null;
        this.details = null;
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

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<DetailReservationTour> getDetails() {
        return details;
    }

    public void setDetails(List<DetailReservationTour> details) {
        this.details = details;
    }
}
