package com.wvoort.wc2022.scoreservice.model;

import com.wvoort.wc2022.scoreservice.model.fixture.Matches;
import com.wvoort.wc2022.scoreservice.model.predict.Predictions;

public interface ScoreCalculation {

    /**
     *
     * @param predictions
     * @return
     */
    Integer calculateScore(Predictions predictions);

    void setMatches(Matches matches);
}
