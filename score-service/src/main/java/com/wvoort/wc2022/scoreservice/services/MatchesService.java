package com.wvoort.wc2022.scoreservice.services;

import com.wvoort.wc2022.scoreservice.model.fixture.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchesService {

    @Value("${fixtures.api}")
    private String fixtureApi;


    @Autowired
    private RestTemplate restTemplate;

    private String getRawMatches(String api) {

        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(api , String.class);
        return responseEntity.getBody();
    }

    public Matches getMatches() {

        return Matches.fromJson(getRawMatches(fixtureApi));
    }

}
