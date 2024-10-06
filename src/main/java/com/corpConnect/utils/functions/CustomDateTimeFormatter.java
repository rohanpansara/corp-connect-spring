package com.corpConnect.utils.functions;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class CustomDateTimeFormatter {

    // Converts LocalDateTime to String using MEDIUM format style (e.g. Jul 23, 2024, 10:15:30 AM)
    public static String getLocalDateTimeString(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return localDateTime.format(dateTimeFormatter);
    }

    // Converts LocalDate to String using custom pattern
    public static String getLocalDateString(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(dateTimeFormatter);
    }

    // Converts String to LocalDateTime using FULL format style
    public static LocalDateTime getLocalDateTimeObject(String localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        return LocalDateTime.parse(localDateTime, dateTimeFormatter);
    }

    // Converts String to LocalDate using custom pattern
    public static LocalDate getLocalDateObject(String localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(localDate, dateTimeFormatter);
    }
}
