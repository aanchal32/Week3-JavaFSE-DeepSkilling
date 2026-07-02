package com.cognizant.spring_learn.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "countries")
public class CountryList {

    @JacksonXmlProperty(localName = "country")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Country> country;

    public CountryList() {
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }
}
