package com.cognizant.spring_learn.util;

import com.cognizant.spring_learn.model.CountryList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class XmlReaderUtil {

    public CountryList readCountries(String resourcePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        try (InputStream inputStream = resource.getInputStream()) {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(inputStream, CountryList.class);
        }
    }
}
