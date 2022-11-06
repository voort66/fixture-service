package com.wvoort.wc2022.fixtureservice.service;

import com.wvoort.wc2022.fixtureservice.model.Matches;

public interface FixtureService {
    Matches getFixtures();

    Matches getLiveFixtures();

    Matches getFixturesToday();

}
