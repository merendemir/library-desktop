package com.application.library.desktop.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm.ss");

    public static String getLocalDate(LocalDateTime date) {
        return dateFormatter.format(date);
    }

    public static String formatDateString(String dateString) {
        try {
            String dateSubstring = dateString.substring(0, dateString.lastIndexOf("."));
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateSubstring, inputFormatter);

            ZoneId utcZone = ZoneId.of("UTC");
            LocalDateTime localDateTime = dateTime.atZone(utcZone).toLocalDateTime();

            return dateFormatter.format(localDateTime);
        } catch (Exception e) {
            return dateString;
        }
    }

}
