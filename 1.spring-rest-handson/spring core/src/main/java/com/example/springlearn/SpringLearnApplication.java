package com.example.springlearn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpringLearnApplication {

    public static void displayDate() {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("date-format.xml");

        SimpleDateFormat format =
                context.getBean("dateFormat", SimpleDateFormat.class);

        try {
            Date date = format.parse("31/12/2018");
            System.out.println(date);
        } catch (ParseException e) {
            System.err.println("Failed to parse date: " + e.getMessage());
        } finally {
            ((ClassPathXmlApplicationContext) context).close();
        }
    }

    public static void main(String[] args) {
        displayDate();
    }
}
