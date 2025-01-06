package com.example.demosun2025.controller;

import com.example.demosun2025.model.Interval;
import com.example.demosun2025.model.Location;
import com.example.demosun2025.service.MoonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoonRestControllerMockMvcIntTest {

    public static final Location DEFAULT_LOCATION = new Location(52.379189, 4.899431);
    private static final String DEFAULT_TZ = "Europe/Amsterdam";
    private static final int DEFAULT_AMOUNT = 7;
    private static final Interval DEFAULT_INTERVAL = Interval.DAY;
    private static final Instant DEFAULT_NOW = Instant.parse("2042-01-01T12:15:00Z");

    @MockitoBean
    private Clock clock;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MoonService moonService;

    @BeforeEach
    void beforeEach() {
        when(clock.instant()).then(invocation -> DEFAULT_NOW);
        when(clock.getZone()).then(invocationOnMock -> ZoneId.of(DEFAULT_TZ));
    }

    @Test
    void today() throws Exception {
        this.mockMvc.perform(get("/moons/today")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}"));
    }

    @Test
    void future_amount_7() throws Exception {
        this.mockMvc.perform(get("/moons/future").param("amount", String.valueOf(DEFAULT_AMOUNT))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));
    }

    @Test
    void future_interval_DAY() throws Exception {
        this.mockMvc.perform(get("/moons/future").param("interval", String.valueOf(DEFAULT_INTERVAL))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));
    }

    @Test
    void future_location_Ams() throws Exception {
        this.mockMvc.perform(get("/moons/future").param("location", String.valueOf(DEFAULT_LOCATION))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));
    }

    @Test
    void future_tz_Ams() throws Exception {
        this.mockMvc.perform(get("/moons/future").param("tz", DEFAULT_TZ)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));
    }

    @Test
    void future() throws Exception {
        this.mockMvc.perform(get("/moons/future")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-02\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-03\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-04\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-05\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-06\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2042-01-07\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));

    }

    @Test
    void past() throws Exception {
        this.mockMvc.perform(get("/moons/past")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));

    }

    @Test
    void past_amount_7() throws Exception {
        this.mockMvc.perform(get("/moons/past").param("amount", String.valueOf(DEFAULT_AMOUNT))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));

    }

    @Test
    void past_interval_DAY() throws Exception {
        this.mockMvc.perform(get("/moons/past").param("interval", String.valueOf(DEFAULT_INTERVAL))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));
    }

    @Test
    void past_location_Ams() throws Exception {
        this.mockMvc.perform(get("/moons/past").param("location", String.valueOf(DEFAULT_LOCATION))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));
    }

    @Test
    void past_tz_Ams() throws Exception {
        this.mockMvc.perform(get("/moons/past").param("tz", DEFAULT_TZ)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"date\":\"2042-01-01\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-31\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-30\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-29\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-28\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-27\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"},{\"date\":\"2041-12-26\",\"rise\":null,\"set\":null,\"location\":{\"latitude\":52.379189,\"longitude\":4.899431},\"timezone\":\"Europe/Amsterdam\"}]"));
    }
}