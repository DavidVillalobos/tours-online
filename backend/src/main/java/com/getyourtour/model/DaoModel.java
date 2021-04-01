/*
 * File: DaoModel.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour;

import java.util.ArrayList;
import java.util.List;

public class DaoModel{
    
    private DaoCountry dao_country;

    public DaoModel(){
        this.dao_country = new DaoCountry();
    }

    /* - - - - - COUNTRIES - - - - -  */
    public Country getCountry(int Id){
        return dao_country.get(Id);
    }
    public List<Country> getAllCountries(){
        return dao_country.get();
    }
    public int addCountry(Country c){
        return dao_country.post(c);
    }
    public int updateCountry(Country c){
        return dao_country.put(c);
    }
    public int deleteCountry(int id){
        return dao_country.delete(id);
    }
}