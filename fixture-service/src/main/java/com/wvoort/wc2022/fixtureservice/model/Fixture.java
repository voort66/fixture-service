package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Fixture {
    private Long id;

    private String referee;

    private String timeZone;

    private String date;

    private Long timestamp;

    private Periods periods;

    private Venue venue;

    private Status status;

    public String getIsoDate() {
        ZonedDateTime localDateTime = ZonedDateTime.parse(date);
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getShortTime() {
        ZonedDateTime localDateTime = ZonedDateTime.parse(date);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(dtf);

    }


}
