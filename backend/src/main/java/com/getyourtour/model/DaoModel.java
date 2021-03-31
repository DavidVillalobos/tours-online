package com.getyourtour;

import java.util.ArrayList;
import java.util.List;

public class DaoModel{
    
    private DaoCountry dao_country;

    public DaoModel(){
        this.dao_country = new DaoCountry();
    }
    
    public List<Country> getAllCountries(){
        return dao_country.getAllCountries();
    }

}