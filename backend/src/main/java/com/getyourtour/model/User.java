/*
 * File: User.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.model;

import java.util.Date;

public class User {
    private Integer id;
    private Country country;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Integer identification;
    private Date birthday;

    public User() {
        this.id= 0 ;
        this.country = null;
        this.email = "";
        this.password = "";
        this.name = "";
        this.lastName = "";
        this.identification = 0;
        this.birthday = null;
    }

    public User(Integer id, Country country, String email, String password, String name, String lastName, Integer identification, Date birthday) {
        this.id = id;
        this.country = country;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.birthday = birthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
