package com.wvoort.wc2022.wc2022app.model.standings;

import com.google.gson.Gson;
import com.wvoort.wc2022.wc2022app.model.matches.Matches;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Standings {
    @Getter
    private List<Group> groups = new ArrayList<>();

    public static Standings fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, Standings.class);
    }
}
