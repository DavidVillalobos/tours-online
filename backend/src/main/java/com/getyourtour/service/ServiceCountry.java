package com.getyourtour.service;

import com.getyourtour.dao.DaoCountry;
import com.getyourtour.model.Country;

import java.util.List;

public class ServiceCountry {

    private final DaoCountry dao_country = new DaoCountry();

    public Country getCountry(Integer id, Boolean complete) throws Exception {
        return dao_country.get(id, complete);
    }

    public List<Country> getAllCountries(Boolean complete) throws Exception {
        return dao_country.get(complete);
    }

    public int addCountry(Country country) throws Exception {
        if(country.getName().isEmpty()){
            return -1;
        }
        return dao_country.post(country);
    }

    public int updateCountry(Country country) throws Exception {
        return dao_country.put(country);
    }

    public int deleteCountry(Integer id) throws Exception {
        return dao_country.delete(id);
    }

}
