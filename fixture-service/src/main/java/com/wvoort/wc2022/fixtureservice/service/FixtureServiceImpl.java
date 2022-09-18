package com.wvoort.wc2022.fixtureservice.service;

import com.wvoort.wc2022.fixtureservice.model.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureServiceImpl implements FixtureService {

    private final static String MATCHES_CACHE_KEY="matches";

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



    @Cacheable(value = "matchesCache")
    private String getRawFixtures(String matchesName) {
        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(footballApi + "fixtures"+ parameterString , String.class);
        return responseEntity.getBody();
    }


    @Override
    public Matches getFixtures() {
        return Matches.fromJsonResponseString(getRawFixtures(MATCHES_CACHE_KEY));
    }
}
