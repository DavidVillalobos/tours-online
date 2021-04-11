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
    private float commission;
    private float subTotal;
    private float total;
    private List<DetailReservationTour> details;

    public ReservationTour() {
        this.id = 0;
        this.user = null;
        this.commission = 0;
        this.subTotal = 0;
        this.total = 0;
        this.details = null;
    }

    public ReservationTour(Integer id, float commission, float subTotal, float total) {
        this.id = id;
        this.commission = commission;
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

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
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
