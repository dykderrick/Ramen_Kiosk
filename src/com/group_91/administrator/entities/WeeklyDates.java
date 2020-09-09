package com.group_91.administrator.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class defines the "last-week" string array.
 * For example, if the current date is "2020-05-20",
 * the class will have an access to get a String array of "2020-05-10", "2020-05-11", ..., "2020=05-16".
 *
 * @author Yingke Ding
 */
public class WeeklyDates {
    private static String[] weeklyDates;


    /**
     * Constructor.
     */
    public WeeklyDates() {
        setWeeklyDates();
    }

    /**
     * {@link WeeklyDates#weeklyDates} setter.
     * Use {@link Date} and {@link Calendar} to deal with dates.
     * Read the current date, minus 7, and {@link Calendar#getFirstDayOfWeek()} to find the first day of the week.
     * Then recursively add 1 day to the calendar to get all 7 days String of the last week.
     */
    private void setWeeklyDates() {
        weeklyDates = new String[7];
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int j = 0; j < 7; ++j) {
            Date thisDate = c.getTime();
            weeklyDates[j] = simpleDateFormat.format(thisDate);
            c.add(Calendar.DATE, 1);
        }
    }


    /**
     * Getter.
     * @return {@link WeeklyDates#weeklyDates}
     */
    public static String[] getWeeklyDates() {
        return weeklyDates;
    }
}
