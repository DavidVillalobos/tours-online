/*
 * File: DetailReservationTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

public class DetailReservationTour {
    private Integer id;
    private ReservationTour reservationTour;
    private Tour tour;
    private Integer tickets;
    private float total;

    public DetailReservationTour() {
        this.id = 0;
        this.reservationTour = null;
        this.tour = null;
        this.tickets = 0;
        this.total = 0;
    }

    public DetailReservationTour(Integer id, ReservationTour reservationTour, Tour tour, Integer tickets, float total) {
        this.id = id;
        this.reservationTour = reservationTour;
        this.tour = tour;
        this.tickets = tickets;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservationTour getReservationTour() {
        return reservationTour;
    }

    public void setReservationTour(ReservationTour reservationTour) {
        this.reservationTour = reservationTour;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
