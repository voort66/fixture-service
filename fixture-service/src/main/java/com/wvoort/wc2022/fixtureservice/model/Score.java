package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

@Data
public class Score {
    private Goals halfTime;

    private Goals fullTime;

    private Goals extraTime;

    private Goals penalty;
}
