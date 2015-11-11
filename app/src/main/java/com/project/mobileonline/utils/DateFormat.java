package com.project.mobileonline.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nguyendinhduc on 11/11/15.
 */
public class DateFormat {
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static String changeDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return format.format(date);
    }

    public static Date changeStringToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isDateValid(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            format.setLenient(false);
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }
}
