/*
 * File: ServiceCity.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoCity;
import com.getyourtour.model.City;

import java.util.List;

public class ServiceCity {

    private DaoCity dao_city = new DaoCity();

    public City getCity(Integer id){
        return dao_city.get(id);
    }

    public List<City> getAllCountries(){
        return dao_city.get();
    }

    public List<City> getByCountry(Integer id){
        return dao_city.getByCountry(id);
    }

    public int addCity(City city){
        if(city.getName().isEmpty()){
            return -1;
        }
        return dao_city.post(city);
    }

    public int updateCity(City city){
        return dao_city.put(city);
    }

    public int deleteCity(Integer id){
        return dao_city.delete(id);
    }

}
