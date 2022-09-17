package com.wvoort.wc2022.fixtureservice.service;

import com.wvoort.wc2022.fixtureservice.ApplicationConfig;
import com.wvoort.wc2022.fixtureservice.model.Matches;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
class FixtureServiceTest {

    @Autowired
    private FixtureService fixtureService;

    @MockBean
    private RestTemplate restTemplate;

    private ResponseEntity<String> responseEntity;


    private Matches matches;

    @BeforeEach
    void setUp() throws IOException {
        String jsonString = Files.readString(Paths.get("build/resources/test/matches.json"));
        matches = Matches.fromJsonResponseString(jsonString);
        responseEntity =  mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(jsonString);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);

    }


    @Test
    void testFixtures() {
        assertEquals(matches, fixtureService.getFixtures());
    }

}