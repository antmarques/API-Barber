package com.api.barber.model.services.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

    public static Date toDateFormat(String date) {
        try {
            return sdf2.parse(date);
        }catch (ParseException e) {
            throw new RuntimeException("Date isn't formating");
        }
    }

    public static Date toDateTimeFormat(String date) {
        try {
            return sdf.parse(date);
        }catch (ParseException e) {
            throw new RuntimeException("Date isn't formating");
        }
    }

    public static String toStringDateTimeFormat(Date date) {
        return sdf.format(date);
    }

    public static String toStringFormat(Date date) {
        return sdf2.format(date);
    }
}
