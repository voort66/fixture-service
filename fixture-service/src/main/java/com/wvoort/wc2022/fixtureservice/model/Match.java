package com.wvoort.wc2022.fixtureservice.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;


@EqualsAndHashCode
public class Match {

    @Setter
    private Fixture fixture;

    private League league;

    private Teams teams;

    private Goals goals;

    private Score score;

    public String getHomeTeamName() {
        return teams.getHome().getName();
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




}
