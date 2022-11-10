package com.wvoort.wc2022.scoreservice.services;

import com.wvoort.wc2022.scoreservice.model.ScoreCalculation;
import com.wvoort.wc2022.scoreservice.model.predict.RankedUserPredictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserPredictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private MatchesService matchesService;

    @Autowired
    private ScoreCalculation scoreCalculation;

    private List<UserScore> calculateUserScoresAndOrder() {
        List<UserPredictions> userPredictions = predictionService.getUserPredictions();
        scoreCalculation.setMatches(matchesService.getMatches());
        userPredictions.forEach(up -> up.getCalculatedScore(scoreCalculation));

        RankedUserPredictions rankedUserPredictions = new RankedUserPredictions();
        return rankedUserPredictions.rank(userPredictions);
    }

    public UserScore getUserScore(Authentication authentication) {
        List<UserScore> userScores = calculateUserScoresAndOrder();
        UserScore userScore = userScores.stream().
                filter(us -> us.getUserName().equalsIgnoreCase(authentication.getName())).findFirst().orElse(null);
        return userScore;

    }

    public List<UserScore> getTop5() {
        List<UserScore> userScores = calculateUserScoresAndOrder();
        return userScores.subList(0, userScores.size() > 5 ? 5 : userScores.size());

    }


}
