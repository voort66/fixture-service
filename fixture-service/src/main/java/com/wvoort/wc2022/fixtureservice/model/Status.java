package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Status implements Serializable {
    private String longString;

    private String shortString;

    private Integer elapsed;
}
