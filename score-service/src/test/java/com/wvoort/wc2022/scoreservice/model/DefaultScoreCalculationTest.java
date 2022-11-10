package com.wvoort.wc2022.scoreservice.model;

import com.wvoort.wc2022.scoreservice.config.AppConfig;
import com.wvoort.wc2022.scoreservice.model.fixture.Matches;
import com.wvoort.wc2022.scoreservice.model.predict.Predictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserPredictions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
@TestPropertySource(properties = {
        "points.outcome.correct=10",
        "points.outcome.pattern.correct=3"
})
class DefaultScoreCalculationTest {

    @Autowired
    private ScoreCalculation scoreCalculation;

    private Matches matches;

    private List<UserPredictions> userPredictions = new ArrayList<>();

    @BeforeEach
    void setUp() throws IOException {
        String jsonString = Files.readString(Paths.get("build/resources/test/predictions.json"));
        Predictions predictions = Predictions.fromJson(jsonString);
        userPredictions = predictions.initUserPredictions();

        jsonString = Files.readString(Paths.get("build/resources/test/matches.json"));
        matches = Matches.fromJson(jsonString);

        scoreCalculation.setMatches(matches);

    }


    @Test
    void testScoreCalculationWithActualRandomScores() {
        randomizeScores();
        randomizePredictions();

        userPredictions.stream().forEach(up ->up.getCalculatedScore(scoreCalculation));

        userPredictions.forEach(up -> {
            assertNotNull(up.getCalculatedScore());
            System.out.println("user " + up.getUser() + " has " + up.getCalculatedScore() + " points");
        });
    }

    private void randomizeScores() {
        matches.getMatchesList().forEach(m -> {
           Random random = new Random();
           Integer home = random.nextInt(6);
           Integer away = random.nextInt(6);
           m.setMatchScore(home + "-" + away);
           m.setStartDate(LocalDate.now().toString());

        });
    }

    private void randomizePredictions() {
        for (UserPredictions userPrediction : userPredictions) {
            userPrediction.getPredictions().getPredictions().forEach(p -> {
                Random random = new Random();
                Integer home = random.nextInt(6);
                Integer away = random.nextInt(6);
                p.setHomeGoals(home);
                p.setAwayGoals(away);
            });
        }
    }

    @Test
    public void testCalculateScoresBeforeTournamentStarted() {
        userPredictions.stream().forEach(up ->up.getCalculatedScore(scoreCalculation));

        userPredictions.forEach(up -> {
            assertNotNull(up.getCalculatedScore());
            assertEquals(0, up.getCalculatedScore());
        });
    }


}