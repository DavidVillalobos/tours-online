/*
 * File: City.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

public class City {
    private Integer id;
    private Country country;
    private String name;

    public City(){
        this.id = 0;
        this.country = null;
    }

    public City(Integer id, Country country, String name) {
        this.id = id;
        this.country = country;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
