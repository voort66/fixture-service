package com.wvoort.wc2022.scoreservice.services;

import com.wvoort.wc2022.scoreservice.model.ScoreCalculation;
import com.wvoort.wc2022.scoreservice.model.predict.Predictions;
import com.wvoort.wc2022.scoreservice.model.predict.UserPredictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredictionService {

    @Value("${predictions.api}")
    private String predictionsApi;

    @Autowired
    private MatchesService matchesService;



    @Autowired
    private RestTemplate restTemplate;


    private void addAuthenticationHeaderToRestTemplate() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ScoreAuthenticationHeaderInterceptor());
        restTemplate.setInterceptors(interceptors);
    }


    private String getRawPredictions(String api) {

        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(api, String.class);
        return responseEntity.getBody();
    }


    public List<UserPredictions> getUserPredictions() {
        addAuthenticationHeaderToRestTemplate();
        Predictions predictions = Predictions.fromJson(getRawPredictions(predictionsApi));
        return predictions.initUserPredictions();

    }





}
