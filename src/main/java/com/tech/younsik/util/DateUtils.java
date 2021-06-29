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
    /*해당 형식의 데이타를 특정 포맷으로 변환해서 리턴 */
    public static String convertToString(LocalDateTime dateData, String pattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return formatter.format(dateData);
    }
    /*해당 형식의 데이타를 특정 포맷으로 변환해서 리턴 */
    public static LocalDateTime convertToLocalDateTime(String dateData, String pattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return LocalDateTime.parse(dateData, formatter);
    }

    /*현재 날짜를 특정 포맷으로 변환해서 리턴 */
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
    /**
     * Y년 M월 D일(KST)
     *
     * @param year Y년
     * @param month M월
     * @param day D일
     * @return
     */
    public static LocalDateTime getFirstDateTime(int year, int month, int day) {
        ZonedDateTime firstDateTime =
            ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        return firstDateTime.toLocalDateTime();
    }
    /**
     * Y년 M월 D일(KST)
     *
     * @param year Y년
     * @param month M월
     * @param day D일
     * @return
     */
    public static LocalDateTime getLastDateTime(int year, int month, int day) {
        ZonedDateTime firstDateTime =
            ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        ZonedDateTime lastDateTime = firstDateTime.plusDays(1).minusNanos(1);
        return lastDateTime.toLocalDateTime();
    }
    /**
     * Y년 M월 D일(KST)
     *
     * @param year Y년
     * @param month M월
     * @param month D일
     * @param hour H시
     * @return
     */
    public static LocalDateTime getFirstDateTime(int year, int month, int day, int hour) {
        ZonedDateTime firstDateTime =
            ZonedDateTime.of(year, month, day, hour, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        return firstDateTime.toLocalDateTime();
    }
    /**
     * Y년 M월 D일 H시(KST)
     *
     * @param year Y년
     * @param month M월
     * @param month D일
     * @param hour H시
     * @return
     */
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
