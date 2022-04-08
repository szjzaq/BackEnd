package com.example.backend.main.service;

import com.example.backend.main.domain.entity.Country;

import java.util.List;
import java.util.Map;

public interface CountryService {

    Map<String,List<Country>> getCountries();

    int addCountry(Country country);

    List<Map<String,Object>> selectTopFiveCountries();

}
