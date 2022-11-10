package com.wvoort.wc2022.scoreservice.services;

import com.wvoort.wc2022.scoreservice.model.ScoreCalculation;
import com.wvoort.wc2022.scoreservice.model.predict.Predictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserPredictions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PredictionService.class })
class PredictionServiceTest {

    private Predictions predictions;

    @Autowired
    private PredictionService predictionService;

    @MockBean
    private MatchesService matchesService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ScoreCalculation scoreCalculation;

    private ResponseEntity<String> responseEntity;


    @BeforeEach
    void setUp() throws IOException {
        String jsonString = Files.readString(Paths.get("build/resources/test/predictions.json"));
        predictions = Predictions.fromJson(jsonString);
        responseEntity =  mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(jsonString);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);
    }

    @Test
    public void testPredictionService() {

        List<UserPredictions> userPredictions = predictionService.getUserPredictions();
        assertNotNull(userPredictions);
        assertEquals(3, userPredictions.size());

        for(UserPredictions up : userPredictions) {
            assertEquals(48, up.getPredictions().getPredictions().size());
        }

    }


}