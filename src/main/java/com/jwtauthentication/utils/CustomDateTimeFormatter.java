package com.jwtauthentication.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class CustomDateTimeFormatter {
    public static String formatLocalDateTime(LocalDateTime localDateTime){
        DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        return localDateTime.format(dateTimeFormater);
    }

    public static String formatLocalDate(LocalDate localDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(dateTimeFormatter);
    }
}
