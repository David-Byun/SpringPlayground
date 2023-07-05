package com.mang.atdd.movie;

import java.time.LocalDateTime;

public class DateTimePeriod {
    private LocalDateTime from;
    private LocalDateTime to;

    public DateTimePeriod(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public static DateTimePeriod between(LocalDateTime from, LocalDateTime to) {
        return new DateTimePeriod(from, to);
    }

    public DateTimePeriod() {
    }

    public boolean contains(LocalDateTime datetime) {
        return (datetime.isAfter(from) || datetime.equals(from)) && (datetime.isBefore(to) || datetime.equals(to));
    }

}
