/*
 * File: Tour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

import java.sql.Time;

public class Tour {
    private Integer id;
    private Country country;
    private String name;
    private String category;
    private String description;
    private Integer quota;
    private Time duration;
    private float price;
    private Short rating;
    private String includes;
    private String notIncludes;

    public Tour() {
        this.id = 0;
        this.country = null;
        this.name = "";
        this.category  = "";
        this.description = "";
        this.quota = 0;
        this.duration = Time.valueOf("00:00");
        this.price = 0;
        this.rating = 0;
        this.includes = "";
        this.notIncludes = "";
    }

    public Tour(Integer id, Country country, String name, String category, String description, Integer quota, Time duration, float price, Short rating, String includes, String notIncludes) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.category = category;
        this.description = description;
        this.quota = quota;
        this.duration = duration;
        this.price = price;
        this.rating = rating;
        this.includes = includes;
        this.notIncludes = notIncludes;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getNotIncludes() {
        return notIncludes;
    }

    public void setNotIncludes(String notIncludes) {
        this.notIncludes = notIncludes;
    }
}
