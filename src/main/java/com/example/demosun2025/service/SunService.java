package com.example.demosun2025.service;

import com.example.demosun2025.model.Location;
import lombok.extern.slf4j.Slf4j;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTimestamp;
import net.time4j.TemporalType;
import net.time4j.calendar.astro.SolarTime;
import net.time4j.tz.TZID;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class SunService {

	public LocalDateTime getSunrise(TZID id, PlainDate plainDate, Location location) {
		SolarTime amsterdam = SolarTime.ofLocation(location.getLatitude(), location.getLongitude());
		Optional<Moment> sunrise = plainDate.get(amsterdam.sunrise());
		if (sunrise.isPresent()) {
			PlainTimestamp sunrisePlainTimestamp = sunrise.get().toZonalTimestamp(id.canonical());
			return TemporalType.LOCAL_DATE_TIME.from(sunrisePlainTimestamp);

		}
		return null;

	}

	public LocalDateTime getSunSet(TZID id, PlainDate plainDate, Location location) {
		SolarTime amsterdam = SolarTime.ofLocation(location.getLatitude(), location.getLongitude());
		Optional<Moment> sunset = plainDate.get(amsterdam.sunset());
		if (sunset.isPresent()) {
			PlainTimestamp sunsetPlainTimestamp = sunset.get().toZonalTimestamp(id.canonical());
			return TemporalType.LOCAL_DATE_TIME.from(sunsetPlainTimestamp);
		}
		return null;

	}

}
