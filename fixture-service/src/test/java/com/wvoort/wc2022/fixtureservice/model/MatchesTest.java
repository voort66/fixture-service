package com.wvoort.wc2022.fixtureservice.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatchesTest {

    @Test
    void testCreatesMatches() throws IOException {
        String jsonString = Files.readString(Paths.get("build/resources/test/matches.json"));

        Matches allMatches = Matches.fromJsonResponseString(jsonString);
        assertNotNull(allMatches);
        assertEquals(48, allMatches.getMatchesList().size());

    }

}