package utilities;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    private DateUtil() {
    }

    private static final Logger LOGGER = Logger.getLogger(DateUtil.class);

    /**
     * Common date time patterns
     * MM/dd/yyyy	01/02/2018
     * dd-M-yyyy hh:mm:ss	02-1-2018 06:07:59
     * dd MMMM yyyy	02 January 2018
     * dd MMMM yyyy zzzz	02 January 2018 India Standard Time
     * E, dd MMM yyyy HH:mm:ss z	Tue, 02 Jan 2018 18:07:59 IST
     */
    public static final String DATE_TIME_PATTERN_DD_MM_YYY = "dd/MM/yyyy";
    public static final String DATE_TIME_PATTERN_E_MMM_DD = "E, MMM dd";

    public static Date inDaysFromDate(final String date, final int numberOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(convertStringToDate(date, DATE_TIME_PATTERN_DD_MM_YYY)));

        calendar.add(Calendar.DATE, numberOfDays);
        return calendar.getTime();
    }

    public static Date inDaysFromDate(final Date date, final int numberOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, numberOfDays);
        return calendar.getTime();
    }

    public static Date inMonthsFromDate(final String date, final int numberOfMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(convertStringToDate(date, DATE_TIME_PATTERN_DD_MM_YYY)));

        calendar.add(Calendar.MONTH, numberOfMonths);
        return calendar.getTime();
    }

    public static Date inDaysFromToday(final int numberOfDays) {
        Calendar calendar = Calendar.getInstance();
        if (numberOfDays != 0) {
            calendar.add(Calendar.DATE, numberOfDays);
        }
        return calendar.getTime();
    }

    public static String inDaysFromTodayToString(final int numberOfDays) {
        return convertDateToString(inDaysFromToday(numberOfDays));
    }

    public static Date nextMonthFromToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static Date nextMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static String dateInNextMonthToString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return convertDateToString(calendar.getTime(), DATE_TIME_PATTERN_DD_MM_YYY);
    }

    public static int getDayOfMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static Date inOneWeek() {
        return inDaysFromToday(7);
    }

    public static Date inTwoWeeks() {
        return inDaysFromToday(14);
    }

    public static Date inThreeWeeks() {
        return inDaysFromToday(21);
    }

    public static Date inFourWeeks() {
        return inDaysFromToday(28);
    }

    public static String convertDateToString(Date date, String format) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
        return mySimpleDateFormat.format(date);
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN_DD_MM_YYY);
        return mySimpleDateFormat.format(date);
    }

    public static Date convertStringToDate(String dateStr, String format) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
        try {
            return mySimpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * convert dd/MM/yyyy to dd.MM.yyyy
     **/
    public static String convertStringToNewDateFormat(String dateStr, String format) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN_DD_MM_YYY);
        try {
            Date formatted = mySimpleDateFormat.parse(dateStr);
            mySimpleDateFormat = new SimpleDateFormat(format);
            return mySimpleDateFormat.format(formatted);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Integer getMinutesFromMidnight() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    }

    public static Date inDaysFromDate(final String date, final int numberOfDays, final String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(convertStringToDate(date, dateFormat)));

        calendar.add(Calendar.DATE, numberOfDays);
        return calendar.getTime();
    }

    public static Date lastDayFromXMonth(int xMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, xMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date nextMonth(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.YEAR, day);
        return calendar.getTime();
    }

    public static Date convertStringToDate(String dateStr) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN_DD_MM_YYY);
        try {
            return mySimpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static String getTodayByString(String pattern) {
        return new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
    }

    public static String getDateFromTodayToXMonth(int numberOfMonths, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, numberOfMonths);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public static String getYearAfter(int numberOfYear) {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + numberOfYear);
    }

}
