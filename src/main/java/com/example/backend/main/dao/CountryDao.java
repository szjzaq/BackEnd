package com.example.backend.main.dao;

import com.example.backend.main.domain.entity.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountryDao {

    List<Country> getCountries();

    int addCountry(Country country);

}
