/*
 * File: Tour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Tour {
    private Integer id;
    private City city;
    private List<LikeTour> likes;
    private List<ImageTour> images;
    private List<CommentTour> comments;
    private String name;
    private String category;
    private String description;
    private Date date;
    private Integer quota;
    private Integer reviews;
    private Time duration;
    private float price;
    private Short rating;
    private String includes;
    private String notIncludes;

    public Tour() {
        this.id = 0;
        this.city = null;
        this.name = "";
        this.category  = "";
        this.description = "";
        this.date = new Date();
        this.quota = 0;
        this.reviews = 0;
        this.duration = Time.valueOf("00:00:00");
        this.price = 0;
        this.rating = 0;
        this.includes = "";
        this.notIncludes = "";
        likes = null;
        images = null;
        comments = null;
    }

    public Tour(Integer id, String name, String category, String description, Date date, Integer quota, Integer reviews, Time duration, float price, Short rating, String includes, String notIncludes) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.date = date;
        this.quota = quota;
        this.reviews = reviews;
        this.duration = duration;
        this.price = price;
        this.rating = rating;
        this.includes = includes;
        this.notIncludes = notIncludes;
        this.city = null;
        this.likes = null;
        this.images = null;
        this.comments = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStringDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
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

    public List<LikeTour> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeTour> likes) {
        this.likes = likes;
    }

    public List<ImageTour> getImages() {
        return images;
    }

    public void setImages(List<ImageTour> images) {
        this.images = images;
    }

    public List<CommentTour> getComments() {
        return comments;
    }

    public void setComments(List<CommentTour> comments) {
        this.comments = comments;
    }
}
