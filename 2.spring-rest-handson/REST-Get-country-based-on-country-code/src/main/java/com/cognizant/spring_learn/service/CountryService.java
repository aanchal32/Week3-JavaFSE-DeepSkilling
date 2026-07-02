package com.cognizant.spring_learn.service;

import com.cognizant.spring_learn.model.Country;
import com.cognizant.spring_learn.model.CountryList;
import com.cognizant.spring_learn.util.XmlReaderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class CountryService {

    private static final String COUNTRY_XML_PATH = "country.xml";

    private final XmlReaderUtil xmlReaderUtil;

    public CountryService(XmlReaderUtil xmlReaderUtil) {
        this.xmlReaderUtil = xmlReaderUtil;
    }

    public Country getCountry(String code) {
        List<Country> countries = loadCountries();

        return countries.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
    }

    private List<Country> loadCountries() {
        try {
            CountryList countryList = xmlReaderUtil.readCountries(COUNTRY_XML_PATH);
            return countryList.getCountry();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to read country data", e);
        }
    }
}
