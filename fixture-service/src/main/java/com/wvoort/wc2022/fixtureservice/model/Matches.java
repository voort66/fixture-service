package com.wvoort.wc2022.fixtureservice.model;



import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@EqualsAndHashCode
public class Matches implements Serializable {


    private List<Match> matchesList = new ArrayList<>();

    public Matches(List<Match> matches) {
        this.matchesList = matches;
    }


    public Match findMatchByFixtureId(final Long fixtureId) {
        return null;
    }

    public static Matches fromJsonResponseString(final String jsonResponseString) {
        JsonElement jsonElement = JsonParser.parseString(jsonResponseString);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement responseElement = jsonObject.get("response");

        Match[] matches = new Gson().fromJson(responseElement.toString(), Match[].class);
        return new Matches(Arrays.asList(matches));
    }

    public List<Match> getMatchesList() {
        return Collections.unmodifiableList(matchesList).stream().sorted().collect(Collectors.toList());
    }

    public Matches matchesToday() {
        return new Matches(Collections.unmodifiableList(matchesList).stream().
                        filter(Match::isToday).sorted().collect(Collectors.toList()));

    }



}
