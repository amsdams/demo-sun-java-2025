package com.example.demosun2025.controller;

import com.example.demosun2025.model.Interval;
import com.example.demosun2025.model.Location;
import com.example.demosun2025.model.Sun;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SunRestControllerIntTest {

    private static final String DEFAULT_TZ = "Europe/Amsterdam";
    public static final Location DEFAULT_LOCATION = new Location(52.379189, 4.899431);
    private static final int DEFAULT_AMOUNT = 7;
    private static final Interval DEFAULT_INTERVAL = Interval.DAY;


    @Autowired
    private SunRestController sunRestController;

    @Test
    void today() {
        Sun moon = sunRestController.today(DEFAULT_TZ, DEFAULT_LOCATION);
        assertNotNull(moon);


    }

    @Test
    void future() {
        List<Sun> moons = sunRestController.future(DEFAULT_TZ, DEFAULT_AMOUNT, DEFAULT_INTERVAL, DEFAULT_LOCATION);
        assertNotNull(moons);
    }

    @Test
    void past() {
        List<Sun> moons = sunRestController.past(DEFAULT_TZ, DEFAULT_AMOUNT, DEFAULT_INTERVAL, DEFAULT_LOCATION);
        assertNotNull(moons);
    }
}