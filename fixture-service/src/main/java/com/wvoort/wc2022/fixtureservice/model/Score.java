package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Score implements Serializable {
    private Goals halfTime;

    private Goals fullTime;

    private Goals extraTime;

    private Goals penalty;
}
