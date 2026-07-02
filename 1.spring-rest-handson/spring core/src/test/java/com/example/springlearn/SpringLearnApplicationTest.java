package com.example.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpringLearnApplicationTest {

    @Test
    void dateFormatBeanParsesDateCorrectly() throws ParseException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("date-format.xml");

        SimpleDateFormat format =
                context.getBean("dateFormat", SimpleDateFormat.class);

        assertNotNull(format);

        Date parsedDate = format.parse("31/12/2018");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parsedDate);

        assertEquals(2018, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.DECEMBER, calendar.get(Calendar.MONTH));
        assertEquals(31, calendar.get(Calendar.DAY_OF_MONTH));

        ((ClassPathXmlApplicationContext) context).close();
    }
}
