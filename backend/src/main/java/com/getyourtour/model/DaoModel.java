/*
 * File: DaoModel.java
 * author: David Villalobos
 * Date: 2021/04/01
 */
package com.getyourtour.model;

import com.getyourtour.logic.Country;
import java.util.List;

public class DaoModel{
    
    private DaoCountry dao_country;

    public DaoModel(){
        this.dao_country = new DaoCountry();
    }

    /* - - - - - - - - - COUNTRIES - - - - - - - - -  */

    public Country getCountry(Integer id){
        return dao_country.get(id);
    }

    public List<Country> getAllCountries(){
        return dao_country.get();
    }

    public int addCountry(Country country){
        if(country.getName().isEmpty()){
            return -1;
        }
        return dao_country.post(country);
    }

    public int updateCountry(Country country){
        return dao_country.put(country);
    }

    public int deleteCountry(Integer id){
        return dao_country.delete(id);
    }

}