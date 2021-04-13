/*
 * File: Country.java
 * author: David Villalobos
 * Date: 2021/04/01
 */

package com.getyourtour.model;

import java.util.List;

public class Country{

    private Integer id;
    private String name;
    private List<City> cities;

    public Country(){
        this.id = 0;
        this.name = "";
        this.cities = null;
    }

    public Country(Integer id, String name){
        this.id = id;
        this.name = name;
        this.cities = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}