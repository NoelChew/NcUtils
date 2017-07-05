package com.noelchew.ncutils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
    private static Long MILLISECS_PER_DAY = 86400000L;

    public static Calendar getCalendarFromDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Date getFirstDateOfYear(Date date) {
        String firstDateOfYear = dateToString(date, "yyyy") + "0101";
        return getDateFromString(firstDateOfYear, "yyyyMMdd");
    }

    public static Date getLastDateOfYear(Date date) {
        String lastDateOfYear = dateToString(date, "yyyy") + "1231";
        return getDateFromString(lastDateOfYear, "yyyyMMdd");
    }

    public static String convertDateDisplayFormat(String date, String formatIn, String formatOut) {
        SimpleDateFormat sdfIn = new SimpleDateFormat(formatIn);
        SimpleDateFormat sdfOut = new SimpleDateFormat(formatOut);
        String displayDate;
        try {
            displayDate = sdfOut.format(sdfIn.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            displayDate = "";
        }
        return displayDate;
    }

    public static Date getDateFromString(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String dateToString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String dateToString(Date date, String format, Locale locale) {
        DateFormat df = new SimpleDateFormat(format, locale);
        return df.format(date);
    }

    public static boolean datesAreEqual(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate1 = sdf.format(date1);
        String strDate2 = sdf.format(date2);
        return (strDate1.equals(strDate2));
    }

    /***
     *
     * @param date1
     * @param date2
     * @param days
     * @return true if date1 is the number of days before date2
     */
    public static boolean datesAreSpecificDaysBefore(Date date1, Date date2, int days) {
        // remove hour, minute and second components
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date _date1 = date1;
        Date _date2 = date2;
        try {
            _date1 = sdf.parse(sdf.format(date1));
            _date2 = sdf.parse(sdf.format(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long milliseconds = days * MILLISECS_PER_DAY;

        return (_date2.getTime() - _date1.getTime() == milliseconds);
    }

    public static long getTimeInMilliseconds(Date date, int hour, int minute, int second) {
        // remove hour, minute and second components
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date _date = date;
        try {
            _date = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return _date.getTime() + (hour * 3600 + minute * 60 + second) * 1000;
    }

    public static Date getOneDayLater(Date date) {
        return new Date(date.getTime() + MILLISECS_PER_DAY);
    }

    public static Date getOneDayEarlier(Date date) {
        return new Date(date.getTime() - MILLISECS_PER_DAY);
    }

    public static String getMonthFromDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM");
        return sdf.format(date);
    }

    public static Date getDateFromDateComponents(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);

        return calendar.getTime();
    }
}
