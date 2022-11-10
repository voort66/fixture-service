package com.wvoort.wc2022.scoreservice.services;

import com.wvoort.wc2022.scoreservice.config.AppConfig;
import com.wvoort.wc2022.scoreservice.model.fixture.Matches;
import com.wvoort.wc2022.scoreservice.model.predict.Predictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserPredictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ScoreService.class, AppConfig.class  })
@TestPropertySource(properties = {
        "points.outcome.correct=10",
        "points.outcome.pattern.correct=3"
})
class ScoreServiceTest {

    @Autowired
    private ScoreService scoreService;

    @MockBean
    private MatchesService matchesService;

    @MockBean
    private PredictionService predictionService;

    private Authentication authentication;

    private Matches matches;

    private List<UserPredictions> userPredictions;

    @BeforeEach
    void setUp() throws IOException {
        String jsonString = Files.readString(Paths.get("build/resources/test/predictions.json"));
        Predictions predictions = Predictions.fromJson(jsonString);
        userPredictions = predictions.initUserPredictions();
        when(predictionService.getUserPredictions()).thenReturn(userPredictions);

        jsonString = Files.readString(Paths.get("build/resources/test/matches.json"));
        matches = Matches.fromJson(jsonString);
        when(matchesService.getMatches()).thenReturn(matches);

        authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("wim");


    }

    @Test
    void testPersonalScoring() {
        UserScore userScore = scoreService.getUserScore(authentication);
        assertEquals("wim", userScore.getUserName());
        assertEquals(0, userScore.getScore());
        assertEquals(1, userScore.getRank());
    }

    @Test
    void testAllScoring() {
        List<UserScore> userScores = scoreService.getTop5();

        userScores.stream().forEach(userScore -> {
            assertEquals(0, userScore.getScore());
            assertEquals( 1, userScore.getRank());
        });
    }

    @Test
    void testAllScoringWithPredictionsAndScores() {
        randomizeScores();
        randomizePredictions();
        List<UserScore> userScores = scoreService.getTop5();

        userScores.stream().forEach(userScore -> {
            assertNotEquals(0, userScore.getScore());
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
}