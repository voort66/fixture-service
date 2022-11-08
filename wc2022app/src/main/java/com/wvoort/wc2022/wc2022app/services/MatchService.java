package com.wvoort.wc2022.wc2022app.services;

import com.wvoort.wc2022.wc2022app.model.matches.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchService {

    @Value("${fixture.api}")
    private String fixtureApi;

    @Value("${fixture.live.api}")
    private String fixtureLiveApi;

    @Value("${fixture.today.api}")
    private String fixtureTodayApi;

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

    public Matches getLiveMatches() {

        return Matches.fromJson(getRawMatches(fixtureLiveApi));
    }

    public Matches getTodaysMatches() {

        return getMatches().matchesToday();
    }


}
