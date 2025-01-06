package com.example.demosun2025.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UtilRestControllerIntTest {

    @Autowired
    private UtilRestController utilRestController;

    @Test
    void timezones() {
        List<String> timezones = utilRestController.timezones();
        assertNotNull(timezones);
    }
}