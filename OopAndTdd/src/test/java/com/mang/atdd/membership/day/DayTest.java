package com.mang.atdd.membership.day;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;


class DayTest {

    @Test
    void localDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 8, 1, 14, 30, 55);
        System.out.println("localDateTime = " + localDateTime);
        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());
    }

    @Test
    void 두기간차이() {
        LocalDateTime startDT = LocalDateTime.of(2019, 11, 10, 18, 40, 25);
        LocalDateTime endDT = LocalDateTime.of(2021, 8, 1, 14, 30, 55);

        System.out.println("시작일 : "+ startDT.toLocalDate());
        System.out.println("종료일 : "+ endDT.toLocalDate());

        //Period.between() 메서드 : 시작 날짜는 포함되지만 종료 날짜는 제외됨
        Period diff = Period.between(startDT.toLocalDate(), endDT.toLocalDate());
        System.out.printf("두 날짜 사이 기간 : %d년 %d월 %d일", diff.getYears(), diff.getMonths(), diff.getDays());
    }

    @Test
    void 시간분초차이() {
        LocalDateTime startDT = LocalDateTime.of(2019, 11, 10, 18, 40, 25);
        LocalDateTime endDT = LocalDateTime.of(2021, 8, 1, 14, 30, 55);

        System.out.println("시작일 : "+ startDT.toLocalTime());
        System.out.println("종료일 : "+ endDT.toLocalTime());

        Duration diff = Duration.between(startDT.toLocalTime(), endDT.toLocalTime());

        System.out.printf("시간 : %d, 분 : %d, 초 : %d",
                diff.toHours(), diff.toMinutes(), diff.getSeconds());
    }

    @Test
    void TimestampLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        System.out.println("timestamp = " + timestamp);

        System.out.println("timestamp.getTime() = " + timestamp.getTime());

        LocalDateTime localDateTime1 = timestamp.toLocalDateTime();
        System.out.println("localDateTime1 = " + localDateTime1);
        
    }

    @Test
    void LocalDateTimestamp() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate = " + localDate);

        // LocalDate => Timestamp
        Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
        System.out.println("timestamp = " + timestamp);

        LocalDate localDate2 = timestamp.toLocalDateTime().toLocalDate();
        System.out.println("localDate2 = " + localDate2);
    }

    @Test
    void BigDecimal() {

        //문자열로 생성
        BigDecimal bigDecimal9 = new BigDecimal("123.45678");
        System.out.println("bigDecimal9 = " + bigDecimal9);

        //valueOf 생성
        BigDecimal bigDecimal10 = BigDecimal.valueOf(123.456);
        System.out.println("bigDecimal10 = " + bigDecimal10);

        BigDecimal bigDecimal1 = new BigDecimal("100000.12345");
        BigDecimal bigDecimal2 = new BigDecimal("100000");
        BigDecimal bigDecimal3 = new BigDecimal("100000");

        System.out.println(bigDecimal1.add(bigDecimal2));
        System.out.println(bigDecimal1.subtract(bigDecimal2));
        System.out.println(bigDecimal1.multiply(bigDecimal2));
        System.out.println(bigDecimal1.divide(bigDecimal2));
        System.out.println(bigDecimal1.remainder(bigDecimal2));

        int compare1 = bigDecimal1.compareTo(bigDecimal2);
        int compare2 = bigDecimal2.compareTo(bigDecimal3);
        System.out.println("compare1 = " + compare1);
        System.out.println("compare2 = " + compare2);

        BigDecimal value = new BigDecimal("11");
        BigDecimal value2 = BigDecimal.valueOf(3);


        BigDecimal divide = value.divide(value2, 2, RoundingMode.HALF_EVEN);
        System.out.println("divide = " + divide);


    }

}
