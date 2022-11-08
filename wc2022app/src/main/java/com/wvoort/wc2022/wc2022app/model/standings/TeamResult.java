package com.wvoort.wc2022.wc2022app.model.standings;

import lombok.Data;

@Data
public class TeamResult {

    private Integer rank;

    private Team team;

    private Integer played;

    private Integer points;

    private Integer win;

    private Integer lose;

    private Integer draw;

    private Integer goalsDiff;
}
