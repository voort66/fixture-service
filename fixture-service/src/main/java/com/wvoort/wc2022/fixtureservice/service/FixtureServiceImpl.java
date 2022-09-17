package com.wvoort.wc2022.fixtureservice.service;

import com.wvoort.wc2022.fixtureservice.model.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Value("${football.api.base}")
    private String footballApi;

    @Autowired
    private RestTemplate restTemplate;

    private String parameterString = "?league=1&season=2022";

    @PostConstruct
    public void initRestTemplateInterceptors() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderInterceptor("x-apisports-key", "c4dfa1a2ba730fe8845f3aa2404f8490"));
        interceptors.add(new HeaderInterceptor("x-rapidapi-host", "v3.football.api-sports.io"));
        restTemplate.setInterceptors(interceptors);
    }


    @Override
    public Matches getFixtures() {

        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(footballApi + "fixtures"+ parameterString , String.class);
        return Matches.fromJsonResponseString(responseEntity.getBody());
    }

}
