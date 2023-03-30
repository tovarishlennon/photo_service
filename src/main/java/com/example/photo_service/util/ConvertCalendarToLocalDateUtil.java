package com.example.photo_service.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class ConvertCalendarToLocalDateUtil {
    public static LocalDateTime toLocalDate(Calendar calendar) {
        TimeZone tz = calendar.getTimeZone();
        ZoneId zoneId = tz.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zoneId);
    }
}
