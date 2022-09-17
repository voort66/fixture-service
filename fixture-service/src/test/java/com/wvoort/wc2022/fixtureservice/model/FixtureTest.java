package com.wvoort.wc2022.fixtureservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixtureTest {


    @Test
    void testGetIsoDate() {
        Fixture fixture = new Fixture();
        fixture.setDate("2022-11-21T16:00:00+00:00");
        assertEquals("2022-11-21", fixture.getIsoDate());


    }

    @Test
    void testGetShortTime() {
        Fixture fixture = new Fixture();
        fixture.setDate("2022-11-21T16:00:00+00:00");
        assertEquals("16:00", fixture.getShortTime());

    }

}