package com.cognizant.spring_learn.controller;

import com.cognizant.spring_learn.model.Country;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private final Country country;

    public CountryController(@Qualifier("country") Country country) {
        this.country = country;
    }

    @RequestMapping("/country")
    public Country getCountryIndia() {
        return country;
    }
}
