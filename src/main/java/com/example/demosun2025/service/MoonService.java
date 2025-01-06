package com.example.demosun2025.service;

import com.example.demosun2025.model.Location;
import lombok.extern.slf4j.Slf4j;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTimestamp;
import net.time4j.TemporalType;
import net.time4j.calendar.astro.LunarTime;
import net.time4j.tz.TZID;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class MoonService {

    public LocalDateTime getMoonrise(TZID id, PlainDate plainDate, Location location) {
        LunarTime amsterdam = LunarTime.ofLocation(id, location.getLatitude(), location.getLongitude());

        LunarTime.Moonlight moonlight = amsterdam.on(plainDate);
        Optional<Moment> moonrise = moonlight.moonrise();

        if (moonrise.isPresent()) {
            PlainTimestamp moonrisePlainTimestamp = moonrise.get().toZonalTimestamp(id.canonical());
            return TemporalType.LOCAL_DATE_TIME.from(moonrisePlainTimestamp);

        }
        return null;

    }

    public LocalDateTime getMoonSet(TZID id, PlainDate plainDate, Location location) {

        LunarTime amsterdam = LunarTime.ofLocation(id, location.getLatitude(), location.getLongitude());

        LunarTime.Moonlight moonlight = amsterdam.on(plainDate);
        Optional<Moment> moonset = moonlight.moonset();

        if (moonset.isPresent()) {
            PlainTimestamp moonsetPlainTimestamp = moonset.get().toZonalTimestamp(id.canonical());
            return TemporalType.LOCAL_DATE_TIME.from(moonsetPlainTimestamp);
        }
        return null;

    }

}
