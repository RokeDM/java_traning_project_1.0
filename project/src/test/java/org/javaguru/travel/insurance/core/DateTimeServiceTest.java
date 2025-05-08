package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    void DaysBetweenZero() {
        Date date1 = createDate("01.01.2025");
        Date date2 = createDate("01.01.2025");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 0L);
    }

    @Test
    void DaysBetweenPositive() {
        Date date1 = createDate("01.01.2025");
        Date date2 = createDate("09.01.2025");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 8L);
    }

    @Test
    void DaysBetweenNegative() {
        Date date1 = createDate("09.01.2025");
        Date date2 = createDate("01.01.2025");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, -8L);
    }

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}