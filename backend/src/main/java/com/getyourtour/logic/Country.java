/*
 * File: Country.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour.logic;

public class Country{
    
    private int Id;
    private String Name;

    public Country(int Id, String Name){
        this.Id = Id;
        this.Name = Name;
    }
    
    public int getId(){
        return Id;
    }

    public String getName(){
        return Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

}