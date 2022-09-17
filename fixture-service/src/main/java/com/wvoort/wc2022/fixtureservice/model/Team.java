package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

@Data
public class Team {

    private Long id;

    private String name;

    private String logo;

    private Boolean winner;
}
