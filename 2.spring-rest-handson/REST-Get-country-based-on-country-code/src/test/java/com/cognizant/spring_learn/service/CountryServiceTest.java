package com.cognizant.spring_learn.service;

import com.cognizant.spring_learn.model.Country;
import com.cognizant.spring_learn.model.CountryList;
import com.cognizant.spring_learn.util.XmlReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryServiceTest {

    private XmlReaderUtil xmlReaderUtil;
    private CountryService countryService;

    @BeforeEach
    void setUp() throws Exception {
        xmlReaderUtil = Mockito.mock(XmlReaderUtil.class);
        countryService = new CountryService(xmlReaderUtil);

        CountryList countryList = new CountryList();
        countryList.setCountry(Arrays.asList(
                new Country("IN", "India"),
                new Country("US", "United States"),
                new Country("GB", "United Kingdom")
        ));

        Mockito.when(xmlReaderUtil.readCountries("country.xml")).thenReturn(countryList);
    }

    @Test
    void testValidCountryUpperCase() {
        Country result = countryService.getCountry("IN");
        assertEquals("IN", result.getCode());
        assertEquals("India", result.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"in", "In", "iN", "IN"})
    void testCaseInsensitiveMatching(String code) {
        Country result = countryService.getCountry(code);
        assertEquals("IN", result.getCode());
        assertEquals("India", result.getName());
    }

    @Test
    void testInvalidCountryThrowsNotFound() {
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> countryService.getCountry("XX")
        );
        assertEquals(404, exception.getStatusCode().value());
    }
}
