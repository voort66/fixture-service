package com.wvoort.wc2022.fixtureservice.controller;

import com.wvoort.wc2022.fixtureservice.model.Matches;
import com.wvoort.wc2022.fixtureservice.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FixtureController {

    @Autowired
    private FixtureService fixtureService;

    @GetMapping(value = "/fixtures")
    public Matches getFixtures() {
        return fixtureService.getFixtures();
    }



}
