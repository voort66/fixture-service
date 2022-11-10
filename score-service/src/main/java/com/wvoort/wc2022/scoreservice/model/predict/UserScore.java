package com.wvoort.wc2022.scoreservice.model.predict;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserScore {

    @Getter
    private String userName;

    @Getter
    private Integer rank;

    @Getter
    private Integer score;
}
