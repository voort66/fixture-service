package com.wvoort.wc2022.fixtureservice.model;

import com.google.gson.annotations.Expose;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@EqualsAndHashCode
public class Match implements Serializable, Comparable<Match> {

    @Setter
    private Fixture fixture;

    private League league;

    @Setter
    private Teams teams;

    @Setter
    private Goals goals;

    @Setter
    private Score score;

    public String getHomeTeamName() {
        return teams.getHome().getName();
    }

    public Long getMatchId() {
        return fixture.getId();
    }

    public String getAwayTeamName() {
        return teams.getAway().getName();
    }

    public String getVenueName() {
        return fixture.getVenue().getName();
    }

    public String getStartDate() {
        return fixture.getIsoDate();
    }

    public String getStartTime() {
        return fixture.getShortTime();
    }

    public String getMatchScore() {
        if(goals.getHome() == null && score.getHalfTime() == null) {
            return "NS";
        }

        return goals.getHome() + "-" + goals.getAway();
    }

    public String getHomeTeamLogo() {
        return teams.getHome().getLogo();
    }

    public String getAwayTeamLogo() {
        return teams.getAway().getLogo();
    }

    public String getRound() {
        return league.getRound();
    }

    boolean isToday() {
        return LocalDate.now().isEqual(LocalDate.parse(getStartDate()));
    }

    @Override
    public int compareTo(Match match) {
        if(this.getStartDate().compareTo(match.getStartDate()) != 0) {
            return this.getStartDate().compareTo(match.getStartDate());
        }

        return this.getStartTime().compareTo(match.getStartTime());

    }
}
