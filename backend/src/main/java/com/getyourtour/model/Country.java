package com.getyourtour;

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

}