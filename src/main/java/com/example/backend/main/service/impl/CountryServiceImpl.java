package com.example.backend.main.service.impl;

import com.example.backend.main.dao.CountryDao;
import com.example.backend.main.domain.entity.Country;
import com.example.backend.main.service.CountryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * CountryServiceImpl process immigration data by countries and years
 */

@Service
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryDao countryDao;

    @Override
    public Map<String, List<Country>> getCountries() {
        List<Country> countryList = countryDao.getCountries();
        Map<String, List<Country>> map = new HashMap<>();
        selectTopFive(countryList);
        return sortByYear(map, countryList);
    }

    @Override
    public int addCountry(Country country) {
        return countryDao.addCountry(country);
    }

    @Override
    public List<Map<String, Object>> selectTopFiveCountries() {
        List<Country> countryList = countryDao.getCountries();
        return selectTopFive(countryList);
    }

    private static Map<String, List<Country>> sortByYear(Map<String, List<Country>> map, List<Country> countryList) {
        List<Country> byYear;
        for (Country c :
                countryList) {
            if (!c.getCountryName().toLowerCase(Locale.ROOT).equalsIgnoreCase("other countries")) {
                Integer year = c.getYear();
                if (map.get(c.getYear().toString()) == null) {
                    byYear = new ArrayList<>();
                    byYear.add(new Country("Other", year, 0, 0, 0));
                    byYear.add(c);
                } else {
                    byYear = map.get(year.toString());
                    if (byYear.size() < 6) {
                        byYear.add(c);
                    } else {
                        Country temp = c;
                        for (int i = 1; i < byYear.size(); i++) {
                            if (byYear.get(i).compareTo(temp) <= 0) {
                                temp = byYear.set(i, c);
                            }
                        }
                        byYear.get(0).setFemaleCount(byYear.get(0).getFemaleCount() + temp.getFemaleCount());
                        byYear.get(0).setMaleCount(byYear.get(0).getMaleCount() + temp.getMaleCount());
                        byYear.get(0).setTotal(byYear.get(0).getTotal() + temp.getTotal());
                    }
                }
                map.put(year.toString(), byYear);
            } else {
                byYear = map.get(c.getYear().toString());
                byYear.get(0).setFemaleCount(byYear.get(0).getFemaleCount() + c.getFemaleCount());
                byYear.get(0).setMaleCount(byYear.get(0).getMaleCount() + c.getMaleCount());
                byYear.get(0).setTotal(byYear.get(0).getTotal() + c.getTotal());
            }
            map.put(c.getYear().toString(), byYear);
        }
        return map;
    }

    private static List<Map<String, Object>> selectTopFive(List<Country> countries) {
        List<Map<String, Object>> maps = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        Map<String, List<Country>> map = new HashMap<>();
        sortByYear(map, countries);
        for (String key :
                map.keySet()) {
            years.add(Integer.valueOf(key));
        }
        years.sort(Comparator.comparingInt(Integer::intValue));
        List<String> countryNameList = new ArrayList<>();
        List<Country> tempList = map.get(years.get(0).toString());
        for (int i = 1; i < tempList.size(); i++) {
            countryNameList.add(tempList.get(i).getCountryName());
        }

        for (Integer i:
             years) {
            Map<String,Object> objectMap = new HashMap<>();
            objectMap.put("year",i);
            for (Country c :
                    countries) {
                if (countryNameList.contains(c.getCountryName()) && Objects.equals(i, c.getYear())) {
                    objectMap.put(c.getCountryName(),c.getTotal());
                }
            }
            maps.add(objectMap);
        }
        System.out.println(maps);
        return maps;
    }


}
