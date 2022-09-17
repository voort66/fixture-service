package com.wvoort.wc2022.fixtureservice.controller;

import com.wvoort.wc2022.fixtureservice.service.FixtureService;
import com.wvoort.wc2022.fixtureservice.model.Fixture;
import com.wvoort.wc2022.fixtureservice.model.Match;
import com.wvoort.wc2022.fixtureservice.model.Matches;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@WebMvcTest(FixtureController.class)
class FixtureControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FixtureService fixtureService;

    @BeforeEach
    void setUp() {

        Match match = new Match();
        Fixture fixture = new Fixture();
        fixture.setId(1L);
        match.setFixture(fixture);




        Matches matches = new Matches(Collections.singletonList(match));
        when(fixtureService.getFixtures()).thenReturn(matches);
    }

    @Test
    void testGetFixtures() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fixtures")
//                       .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andReturn();
//
//        String resultSS = result.getResponse().getContentAsString();
//        assertNotNull(resultSS);
//        assertEquals("[{'type': 'season'}]", resultSS);
    }

}