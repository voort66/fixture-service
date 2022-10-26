package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Venue implements Serializable {
    private Long id;

    private String name;

    private String city;
}
