/*
 * File: Country.java
 * author: David Villalobos
 * Date: 2021/04/01
 */

package com.getyourtour.logic;

public class Country{

    private Integer id;
    private String name;

    public Country(){
        this.id = 0;
        this.name = "";
    }

    public Country(Integer id, String name){
        this.id = id;
        this.name = name;
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

}