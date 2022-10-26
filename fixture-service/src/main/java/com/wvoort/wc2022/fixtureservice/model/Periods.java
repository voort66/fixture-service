package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Periods implements Serializable {
    private Long first;

    private Long second;
}
