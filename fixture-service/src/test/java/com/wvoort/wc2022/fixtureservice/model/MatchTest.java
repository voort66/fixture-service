package com.wvoort.wc2022.fixtureservice.model;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatchTest {

    private static final String MATCH_JSON = "{\n" +
            "            \"fixture\": {\n" +
            "                \"id\": 855734,\n" +
            "                \"referee\": null,\n" +
            "                \"timezone\": \"UTC\",\n" +
            "                \"date\": \"2022-11-21T16:00:00+00:00\",\n" +
            "                \"timestamp\": 1669046400,\n" +
            "                \"periods\": {\n" +
            "                    \"first\": null,\n" +
            "                    \"second\": null\n" +
            "                },\n" +
            "                \"venue\": {\n" +
            "                    \"id\": null,\n" +
            "                    \"name\": \"Al Thumama Stadium\",\n" +
            "                    \"city\": \"Doha\"\n" +
            "                },\n" +
            "                \"status\": {\n" +
            "                    \"long\": \"Not Started\",\n" +
            "                    \"short\": \"NS\",\n" +
            "                    \"elapsed\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"league\": {\n" +
            "                \"id\": 1,\n" +
            "                \"name\": \"World Cup\",\n" +
            "                \"country\": \"World\",\n" +
            "                \"logo\": \"https://media.api-sports.io/football/leagues/1.png\",\n" +
            "                \"flag\": null,\n" +
            "                \"season\": 2022,\n" +
            "                \"round\": \"Group Stage - 1\"\n" +
            "            },\n" +
            "            \"teams\": {\n" +
            "                \"home\": {\n" +
            "                    \"id\": 13,\n" +
            "                    \"name\": \"Senegal\",\n" +
            "                    \"logo\": \"https://media.api-sports.io/football/teams/13.png\",\n" +
            "                    \"winner\": null\n" +
            "                },\n" +
            "                \"away\": {\n" +
            "                    \"id\": 1118,\n" +
            "                    \"name\": \"Netherlands\",\n" +
            "                    \"logo\": \"https://media.api-sports.io/football/teams/1118.png\",\n" +
            "                    \"winner\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"goals\": {\n" +
            "                \"home\": null,\n" +
            "                \"away\": null\n" +
            "            },\n" +
            "            \"score\": {\n" +
            "                \"halftime\": {\n" +
            "                    \"home\": null,\n" +
            "                    \"away\": null\n" +
            "                },\n" +
            "                \"fulltime\": {\n" +
            "                    \"home\": null,\n" +
            "                    \"away\": null\n" +
            "                },\n" +
            "                \"extratime\": {\n" +
            "                    \"home\": null,\n" +
            "                    \"away\": null\n" +
            "                },\n" +
            "                \"penalty\": {\n" +
            "                    \"home\": null,\n" +
            "                    \"away\": null\n" +
            "                }\n" +
            "            }\n" +
            "        }";


    @Test
    void testJsonToMatch() {

        Match match = new Gson().fromJson(MATCH_JSON, Match.class);
        assertNotNull(match);


    }

    @Test
    void isTodayTest() {
        Match match = new Gson().fromJson(MATCH_JSON, Match.class);
        LocalDate localDate = LocalDate.parse("2022-11-21");
        assertNotNull(localDate);
        System.out.println(LocalDate.now());


        assertFalse(match.isToday());
    }

}