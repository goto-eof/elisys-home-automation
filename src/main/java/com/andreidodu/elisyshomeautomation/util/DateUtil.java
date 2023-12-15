package com.andreidodu.elisyshomeautomation.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    public static Date calculateStartDate(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date calculateEndDate(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getTodayDateWithHour(int hour) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getYesterdayDateWithHour(int hour) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar.getTime();
    }

    public static Date calculate24hAgo(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static Date calculate7dAgo(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        return calendar.getTime();
    }

    public static Date calculate1mAgo(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }


    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
