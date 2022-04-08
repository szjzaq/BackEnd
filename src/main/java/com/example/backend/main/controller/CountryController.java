package com.example.backend.main.controller;


import com.example.backend.main.domain.dto.ReturnStructure;
import com.example.backend.main.domain.entity.Country;
import com.example.backend.main.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Resource
    private CountryService countryService;

    @GetMapping("/getByYear")
    private ReturnStructure getCountries() {
        int code;
        Map<String, Object> map = new HashMap<>();
        String message;
        Map<String, List<Country>> countriesByYear = countryService.getCountries();
        if (countriesByYear != null) {
            code = 200;
            message = "success";
            map.put("Year", countriesByYear);
        } else {
            code = 404;
            message = "error";
        }
        return new ReturnStructure(code, message, map);
    }

    @GetMapping("/getTopFiveCountries")
    private ReturnStructure getTopFiveCountries() {
        int code;
        Map<String, Object> map = new HashMap<>();
        String message;
        List<Map<String,Object>> topFiveCountries = countryService.selectTopFiveCountries();
        if (topFiveCountries != null) {
            code = 200;
            message = "success";
            map.put("Year", topFiveCountries);
        } else {
            code = 404;
            message = "error";
        }
        return new ReturnStructure(code, message, map);
    }


}
