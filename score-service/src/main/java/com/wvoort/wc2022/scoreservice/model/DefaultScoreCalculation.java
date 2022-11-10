package com.wvoort.wc2022.scoreservice.model;

import com.wvoort.wc2022.scoreservice.model.fixture.Match;
import com.wvoort.wc2022.scoreservice.model.fixture.Matches;
import com.wvoort.wc2022.scoreservice.model.predict.Prediction;
import com.wvoort.wc2022.scoreservice.model.predict.Predictions;
import org.springframework.beans.factory.annotation.Value;


public class DefaultScoreCalculation implements ScoreCalculation{

    @Value("${points.outcome.correct}")
    private Integer both;

    @Value("${points.outcome.pattern.correct}")
    private Integer outcomePatternCorrect;


    private Matches matches;

    @Override
    public Integer calculateScore(Predictions predictions) {
        if(matches == null) {
            return 0;
        }

        Matches todayAndBefore = matches.matchesTodayAndBefore();
        return todayAndBefore.getMatchesList().stream().filter(match -> !match.getMatchScore().equalsIgnoreCase("NS"))
                .map(match -> calculatePointsForMatch(match, predictions.getPredictionByMatchId(match.getMatchId()))).
                reduce(0, Integer::sum);


    }

    private Integer calculatePointsForMatch(Match match, Prediction prediction) {
        if(prediction == null ) {
            return 0;
        }

        String score = match.getMatchScore();
        if(score.equalsIgnoreCase("NS")) {
            return 0;
        }

        String[] goals = score.split("-");
        Integer homeGoals = Integer.parseInt(goals[0]);
        Integer awayGoals = Integer.parseInt(goals[1]);

        if(homeGoals == prediction.getHomeGoals() && awayGoals == prediction.getAwayGoals()) {
            return both;
        }

        if((homeGoals > awayGoals && prediction.getHomeGoals() > prediction.getAwayGoals()) ||
                (homeGoals < awayGoals && prediction.getHomeGoals() < prediction.getAwayGoals()) ||
                (homeGoals == awayGoals && prediction.getHomeGoals() == prediction.getAwayGoals())) {
            return outcomePatternCorrect;
        }

        return 0;

    }

    @Override
    public void setMatches(Matches matches) {
        this.matches = matches;
    }
}
