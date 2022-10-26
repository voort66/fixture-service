package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goals implements Serializable {
    private Integer home;

    private Integer away;
}
