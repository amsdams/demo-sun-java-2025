package com.example.demosun2025.controller;

import com.example.demosun2025.model.Interval;
import com.example.demosun2025.model.Location;
import com.example.demosun2025.model.Sun;
import com.example.demosun2025.service.SunService;
import lombok.extern.slf4j.Slf4j;
import net.time4j.PlainDate;
import net.time4j.tz.TZID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("suns")
public class SunRestController {

    private final SunService sunService;

    public SunRestController(SunService sunService) {
        this.sunService = sunService;
    }

    @GetMapping(value = "/today")
    public Sun today(@RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") String tz,
                     @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") Location location) {

        TZID id = () -> tz;
        LocalDate now = LocalDate.now();

        return getSun(location, id, now);
    }

    @GetMapping(value = "/future")
    public List<Sun> future(@RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") String tz,
                            @RequestParam(value = "amount", defaultValue = "7") int amount,
                            @RequestParam(value = "interval", defaultValue = "0") Interval interval,
                            @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") Location location) {

        TZID id = () -> tz;
        List<Sun> suns = new ArrayList<>();

        switch (interval) {
            case DAY:
                for (int i = 0; i < amount; ++i) {
                    LocalDate now = LocalDate.now().plusDays(i);

                    Sun sun = getSun(location, id, now);
                    suns.add(sun);
                }
                break;
            case MONTH:
                for (int i = 0; i < amount; ++i) {
                    LocalDate now = LocalDate.now().plusDays(i);

                    Sun sun = getSun(location, id, now);
                    suns.add(sun);
                }
                break;
            case YEAR:
                for (int i = 0; i < amount; ++i) {
                    LocalDate now = LocalDate.now().plusDays(i);

                    Sun sun = getSun(location, id, now);
                    suns.add(sun);
                }
                break;
            default:
                break;
        }

        return suns;

    }

    private Sun getSun(Location location, TZID id, LocalDate now) {
        PlainDate plainDate = PlainDate.from(now);
        return new Sun(now, sunService.getSunrise(id, plainDate, location),
                sunService.getSunSet(id, plainDate, location), id, location);

    }

    @GetMapping(value = "/past")
    public List<Sun> past(@RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") String tz,
                          @RequestParam(value = "amount", defaultValue = "7") int amount,
                          @RequestParam(value = "interval", defaultValue = "0") Interval interval,
                          @RequestParam(value = "location", defaultValue = "52.379189, 4.899431") Location location) {

        TZID id = () -> tz;
        List<Sun> suns = new ArrayList<>();

        switch (interval) {
            case DAY:
                for (int i = 0; i < amount; ++i) {
                    LocalDate now = LocalDate.now().minusDays(i);

                    Sun sun = getSun(location, id, now);
                    suns.add(sun);
                }
                break;
            case MONTH:
                for (int i = 0; i < amount; ++i) {
                    LocalDate now = LocalDate.now().minusDays(i);

                    Sun sun = getSun(location, id, now);
                    suns.add(sun);
                }
                break;
            case YEAR:
                for (int i = 0; i < amount; ++i) {
                    LocalDate now = LocalDate.now().minusDays(i);

                    Sun sun = getSun(location, id, now);
                    suns.add(sun);
                }
                break;
            default:
                break;
        }

        return suns;

    }

}
