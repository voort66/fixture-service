package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Team implements Serializable {

    private Long id;

    private String name;

    private String logo;

    private Boolean winner;
}
