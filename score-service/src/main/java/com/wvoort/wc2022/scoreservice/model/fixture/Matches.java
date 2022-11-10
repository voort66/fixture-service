package com.wvoort.wc2022.scoreservice.model.fixture;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Matches {

    private List<Match> matchesList = new ArrayList<>();

    public Matches() {

    }

    public Matches(List<Match> matches) {
        this.matchesList = matches;
    }

    public static Matches fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, Matches.class);
    }

    public List<Match> getMatchesList() {
        return Collections.unmodifiableList(matchesList.stream().sorted().collect(Collectors.toList()));
    }

    public Matches matchesToday() {
        return new Matches(Collections.unmodifiableList(matchesList).stream().
                filter(Match::isToday).sorted().collect(Collectors.toList()));

    }

    public Matches matchesTodayAndBefore() {
        return new Matches(Collections.unmodifiableList(matchesList).stream().
                filter(Match::isTodayOrBefore).sorted().collect(Collectors.toList()));
    }

}
