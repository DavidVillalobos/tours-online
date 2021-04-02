/*
 * File: ReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

public class ReservationTour{
    private Integer id;
    private User user;
    private float subTotal;
    private float total;

    public ReservationTour() {
        this.id = 0;
        this.user = null;
        this.subTotal = 0;
        this.total = 0;
    }

    public ReservationTour(Integer id, User user, float subTotal, float total) {
        this.id = id;
        this.user = user;
        this.subTotal = subTotal;
        this.total = total;
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
}
