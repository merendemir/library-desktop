package com.application.library.desktop.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm.ss");

    public static String getLocalDate(LocalDateTime date) {
        return dateFormatter.format(date);
    }

}
