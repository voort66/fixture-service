package com.wvoort.wc2022.wc2022app.controller;

import com.wvoort.wc2022.wc2022app.model.matches.Matches;
import com.wvoort.wc2022.wc2022app.services.MatchService;
import com.wvoort.wc2022.wc2022app.services.PredictionService;
import com.wvoort.wc2022.wc2022app.services.StandingsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(Wc2022Controller.class)
class Wc2022ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;


    @MockBean
    private PredictionService predictionService;

    @MockBean
    private StandingsService standingsService;

    @BeforeEach
    void setUp() throws IOException {
        String jsonString = Files.readString(Paths.get("build/resources/test/matches.json"));
        Matches matches = Matches.fromJson(jsonString);

        when(matchService.getMatches()).thenReturn(matches);
    }

    @Test
    @WithMockUser
    void testLayout() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andReturn();

        String resultSS = result.getResponse().getContentAsString();
        assertNotNull(resultSS);
        System.out.println(resultSS);
    }

}