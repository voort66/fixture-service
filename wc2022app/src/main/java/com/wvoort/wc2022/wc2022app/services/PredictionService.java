package com.wvoort.wc2022.wc2022app.services;

import com.wvoort.wc2022.wc2022app.model.predict.Predictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PredictionService {
    @Value("${predictions.api}")
    private String predictionsApi;

    @Value("${predictions.editable.api}")
    private String predictionsEditableApi;

    @Value("${predictions.save.api}")
    private String predictionsSaveApi;

    @Autowired
    private RestTemplate restTemplate;

    private void addAuthenticationHeaderToRestTemplate(Authentication authentication) {

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new AuthenticationHeaderInterceptor(authentication));
        restTemplate.setInterceptors(interceptors);

    }


    private String getRawPredictions(String api) {

        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(api, String.class);
        return responseEntity.getBody();
    }

    public Predictions getAllPredictions(Authentication authentication) {
        addAuthenticationHeaderToRestTemplate(authentication);
        return Predictions.fromJson(getRawPredictions(predictionsApi));
    }

    public Predictions getEditablePredictions(Authentication authentication) {
        addAuthenticationHeaderToRestTemplate(authentication);
        return Predictions.fromJson(getRawPredictions(predictionsEditableApi));
    }

    public Predictions savePredictions(Authentication authentication, Predictions predictions) {

        addAuthenticationHeaderToRestTemplate(authentication);
        String response =restTemplate.postForObject(predictionsSaveApi, predictions, String.class);

        Predictions savedPredictions = Predictions.fromJson(response);

        return savedPredictions;

    }
}
