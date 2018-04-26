package com.monitor;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Administrator on 2018/4/26.
 */
public class NewDate {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("2018-04-26");
        System.out.println("get localdate is " +localDate);
        DayOfWeek thursday = LocalDate.parse("2017-07-23").getDayOfWeek();
        System.out.println("周四: " + thursday);
        int twenty = LocalDate.parse("2017-07-25").getDayOfMonth();
        System.out.println("twenty: " + twenty);
        LocalDate firstDayOfMonth = LocalDate.parse("2017-07-20")
                .with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("1这个月的最后一天: " + firstDayOfMonth);
        firstDayOfMonth = LocalDate.parse("2017-07-20").withDayOfMonth(1);
        System.out.println("2这个月的第一天: " + firstDayOfMonth);
        LocalDateTime now = LocalDateTime.now();
        now =LocalDateTime.of(2017, Month.JULY, 20, 15, 18);
        LocalDateTime tomorrow = now.plusDays(1);
        LocalDate initialDate = LocalDate.parse("2017-07-20");
        LocalDate finalDate   = LocalDate.of(2018,10,19);
        long between = ChronoUnit.DAYS.between(initialDate, finalDate);
        System.out.println("差距天数: " + between);//5
    }

}
