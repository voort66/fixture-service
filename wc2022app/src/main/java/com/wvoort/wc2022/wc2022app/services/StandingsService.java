package com.wvoort.wc2022.wc2022app.services;


import com.wvoort.wc2022.wc2022app.model.standings.Standings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StandingsService {


    @Value("${standings.api}")
    private String standingsApi;

    @Autowired
    private RestTemplate restTemplate;

    private String getRawStandings() {
        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(standingsApi , String.class);
        return responseEntity.getBody();
    }


    public Standings getStandings() {
        return Standings.fromJson(getRawStandings());
    }
}
