package com.wvoort.wc2022.scoreservice.model.predict;


import com.wvoort.wc2022.scoreservice.model.fixture.Match;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@EqualsAndHashCode
@Data
public class Prediction implements Comparable<Prediction>, Serializable {

    private Long matchId;

    private String userName;

    private Integer homeGoals;

    private Integer awayGoals;

    private Instant creationDate;

    private Instant lastUpdate;

    @Setter
    @Getter
    private Match matchDetails;

    public Prediction() {
        //noop
    }

    public Prediction(Long matchId, String userName, Match matchDetails) {
        this.matchId = matchId;
        this.userName = userName;
        this.matchDetails = matchDetails;
    }


    @Override
    public int compareTo(Prediction p) {
        if(matchId > p.matchId) {
            return 1;
        } else if (matchId < p.matchId) {
            return -1;
        }

        return 0;
    }
}
