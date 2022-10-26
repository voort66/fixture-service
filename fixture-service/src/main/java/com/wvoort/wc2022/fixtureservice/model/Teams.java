package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Teams implements Serializable {
    private Team home;

    private Team away;

}
