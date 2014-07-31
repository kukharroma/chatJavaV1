package com.chat.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Represent methods for work with date.
 *  Change or formatting date.
 */
public class TimeFormater {

    private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat();
    private static final String TIME_WITH_SECONDS_PATTERN = "HH:mm:ss dd.MM.yyyy";

    /**
     * Formats the date object using SimpleDateFormat
     * and some pattern.
     *
     * @param date you want to format
     * @return formatted date
     */
    public static String formatDateWithSeconds(Date date) {
        DATE_FORMATER.applyPattern(TIME_WITH_SECONDS_PATTERN);
        return DATE_FORMATER.format(date);
    }

}
