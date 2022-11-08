package com.wvoort.wc2022.wc2022app.model.predict;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Data
public class Match implements Serializable {

    private Long matchId;

    private String awayTeamName;

    private String venueName;

    private String homeTeamLogo;

    private String awayTeamLogo;

    private String homeTeamName;

    private String startDate;

    private String startTime;

    public Instant startDateTimeAsInstant() {
        return Instant.parse(startDate + "T" + startTime + ":00Z");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return getMatchId().equals(match.getMatchId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatchId());
    }
}
