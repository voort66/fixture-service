package com.wvoort.wc2022.fixtureservice.model;

import lombok.Data;

@Data
public class League {
    /**
     * "league": {
     *                 "id": 1,
     *                 "name": "World Cup",
     *                 "country": "World",
     *                 "logo": "https://media.api-sports.io/football/leagues/1.png",
     *                 "flag": null,
     *                 "season": 2022,
     *                 "round": "Group Stage - 1"
     *             },
     */

    private Long id;

    private String name;

    private String country;

    private String logo;

    private String flag;

    private String season;

    private String round;

}
