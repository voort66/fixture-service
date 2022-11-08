package com.wvoort.wc2022.wc2022app.model.matches;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * startTime: "16:00",
 * round: "Group Stage - 1",
 * venueName: "Al Bayt Stadium",
 * awayTeamLogo: "https://media.api-sports.io/football/teams/2382.png",
 * startDate: "2022-11-20",
 * awayTeamName: "Ecuador",
 * homeTeamLogo: "https://media.api-sports.io/football/teams/1569.png",
 * matchScore: "NS",
 * homeTeamName: "Qatar",
 * matchId: 855736
 */


@Data
public class Match implements Serializable, Comparable<Match> {
    private Integer matchId;

    private String round;

    private String awayTeamLogo;

    private String homeTeamLogo;

    private String matchScore;

    private String homeTeamName;

    private String awayTeamName;

    private String startDate;

    private String startTime;

    @Override
    public int compareTo(Match match) {
        if(this.getStartDate().compareTo(match.getStartDate()) != 0) {
            return this.getStartDate().compareTo(match.getStartDate());
        }

        return this.getStartTime().compareTo(match.getStartTime());

    }

    boolean isToday() {
        return LocalDate.now().isEqual(LocalDate.parse(getStartDate()));
    }
}
