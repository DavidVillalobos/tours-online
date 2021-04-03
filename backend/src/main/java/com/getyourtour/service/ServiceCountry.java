package com.getyourtour.service;

import com.getyourtour.dao.DaoCountry;
import com.getyourtour.model.Country;

import java.util.List;

public class ServiceCountry {

    private final DaoCountry dao_country = new DaoCountry();

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
