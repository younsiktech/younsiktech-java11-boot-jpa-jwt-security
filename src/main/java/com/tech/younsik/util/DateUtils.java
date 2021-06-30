package com.tech.younsik.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    
    public static String LocalDateTimeToISOString(LocalDateTime localDateTime) {
        ZonedDateTime zonedValue = localDateTime.atZone(ZoneOffset.UTC);
        return zonedValue.format(DateTimeFormatter.ISO_INSTANT);
    }
    
    public static String convertToString(LocalDateTime dateData, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(dateData);
    }
    
    public static LocalDateTime convertToLocalDateTime(String dateData, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateData, formatter);
    }
    
    public static String convertNowToString(String pattern) {
        
        return convertToString(LocalDateTime.now(), pattern);
    }
    
    public static LocalDateTime toZone(
        final LocalDateTime time, final ZoneId fromZone, final ZoneId toZone) {
        final ZonedDateTime zonedtime = time.atZone(fromZone);
        final ZonedDateTime converted = zonedtime.withZoneSameInstant(toZone);
        return converted.toLocalDateTime();
    }
    
    public static LocalDateTime toUtc(final LocalDateTime time, final ZoneId fromZone) {
        return DateUtils.toZone(time, fromZone, ZoneOffset.UTC);
    }
    
    public static LocalDateTime getFirstDateTime(int year, int month, int day) {
        ZonedDateTime firstDateTime =
            ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        return firstDateTime.toLocalDateTime();
    }
    
    public static LocalDateTime getLastDateTime(int year, int month, int day) {
        ZonedDateTime firstDateTime =
            ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        ZonedDateTime lastDateTime = firstDateTime.plusDays(1).minusNanos(1);
        return lastDateTime.toLocalDateTime();
    }
    
    public static LocalDateTime getFirstDateTime(int year, int month, int day, int hour) {
        ZonedDateTime firstDateTime =
            ZonedDateTime.of(year, month, day, hour, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        return firstDateTime.toLocalDateTime();
    }
    
    public static LocalDateTime getLastDateTime(int year, int month, int day, int hour) {
        ZonedDateTime firstDateTime =
            ZonedDateTime.of(year, month, day, hour, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        ZonedDateTime lastDateTime = firstDateTime.plusHours(1).minusNanos(1);
        return lastDateTime.toLocalDateTime();
    }
    
    public static long toEpochMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }
    
    public static long getCurrentInstant() {
        return Instant.now().toEpochMilli();
    }
}
