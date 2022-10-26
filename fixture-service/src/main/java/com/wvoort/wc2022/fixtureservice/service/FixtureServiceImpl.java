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

    private static final String FIXTURES_ENDPOINT_BASE="fixtures";

    @Value("${football.api.base}")
    private String footballApi;

    @Value("${api.header.key}")
    private String apiHeaderKeyName;

    @Value("${api.header.key.value}")
    private String apiHeaderKeyValue;

    @Value("${api.header.host}")
    private String apiHeaderHostName;

    @Value("${api.header.host.value}")
    private String apiHeaderHostValue;

    @Value("${api.parameters}")
    private String parameterString;

    @Autowired
    private RestTemplate restTemplate;



    @PostConstruct
    public void initRestTemplateInterceptors() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderInterceptor(apiHeaderKeyName, apiHeaderKeyValue));
        interceptors.add(new HeaderInterceptor(apiHeaderHostName, apiHeaderHostValue));
        restTemplate.setInterceptors(interceptors);
    }




    private String getRawFixtures() {
        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(footballApi + FIXTURES_ENDPOINT_BASE + parameterString , String.class);
        return responseEntity.getBody();
    }


    @Cacheable(value = "fixtures")
    @Override
    public Matches getFixtures() {
        return Matches.fromJsonResponseString(getRawFixtures());
    }
}
