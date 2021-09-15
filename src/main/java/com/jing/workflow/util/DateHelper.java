package com.jing.workflow.util;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateHelper {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String COMPACT_FORMAT = "yyMMdd HH:mm:ss";

    public static final String FILENAME_FORMAT = "yyMMdd_HHmmss";

    public static final String SIMPLE_FORMAT = "yyMMdd";

    public static final String MONTH_DATE = "yyyy-MM";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATEFORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    public static final String STANDARD_FORMAT_OTHER = "yyyy/MM/dd HH:mm";


    public static Timestamp strToTimeStamp(String date,String format){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(date,df);
        return new Timestamp(localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
    }

    public static String getNowDateStr(String format){
        LocalDate date = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return date.format(df);
    }

    public static ZonedDateTime getZonedDateTime(LocalDate date) {
        if (date != null) {
            return ZonedDateTime.of(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth(), 0, 0, 0, 0, ZoneId.systemDefault());
        } else {
            return null;
        }
    }

    public static String getDateStr(LocalDate date,String format){
        if(date == null){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return date.format(df);
    }

    public static String getDateStr(LocalDateTime date,String format){
        if(date == null){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return date.format(df);
    }

    public static String getDateStr(Timestamp date,String format){
        LocalDateTime localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return localDate.format(df);
    }

    public static int calculationBetweenMonths(String date1,String date2,String format){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        LocalDate localDateTime1 = LocalDate.parse(date1,df);
        LocalDate localDateTime2 = LocalDate.parse(date2,df);
        return calculationBetweenMonths(localDateTime1,localDateTime2);
    }

    public static int calculationBetweenMonths(LocalDate date1,LocalDate date2){
        if(date2.isBefore(date1)){
            return calculationBetweenMonths(date2,date1);
        }
        int count = 0;
        LocalDate start = LocalDate.of(date1.getYear(),date1.getMonth(),date1.getDayOfMonth());
        LocalDate end = LocalDate.of(date2.getYear(),date2.getMonth(),date2.getDayOfMonth());
        while(start.isBefore(end)){
            count++;
            start = start.plusMonths(1);
        }
        return count;
    }

    public static LocalDateTime getFirstDayOfMonth(){
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime firstday = date.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        return firstday;
    }

    public static LocalDate getFirstDayOfMonthLocalDate(){
        LocalDate date = LocalDate.now();
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getLastDayOfMonthLocalDate(){
        LocalDate date = LocalDate.now();
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static Timestamp get202101Timestamp(){
        LocalDateTime date = LocalDateTime.of(2021,01,01,0,0,0);
        return new Timestamp(date.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());

    }

    public static Timestamp getFirstDayOfMonthTimestamp(){
        LocalDateTime date = getFirstDayOfMonth();
        return new Timestamp(date.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());

    }

    public static Timestamp getSupposedTimeStamp(LocalDate localDate){
        if(localDate == null){
            return null;
        }
        return new Timestamp(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
    }

    public static LocalDate getSupportLocalDate(Timestamp timestamp){
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return LocalDate.of(localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth());
    }

    public static void main(String[] args) {
        System.out.println( calculationBetweenMonths(LocalDate.of(2020,1,1),LocalDate.of(2020,1,1)));
    }

}


