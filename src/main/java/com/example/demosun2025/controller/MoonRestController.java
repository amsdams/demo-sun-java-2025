package com.example.demosun2025.controller;

import com.example.demosun2025.model.Interval;
import com.example.demosun2025.model.Location;
import com.example.demosun2025.model.Moon;
import com.example.demosun2025.service.MoonService;
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
@RequestMapping("moons")
public class MoonRestController {

	private final MoonService moonService;

	public MoonRestController(MoonService moonService) {
		this.moonService = moonService;
	}

	@GetMapping(value = "/today")
	public Moon today(@RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") String tz,
			@RequestParam(value = "location", defaultValue = "52.379189, 4.899431") Location location) {

		TZID id = () -> tz;
		LocalDate now = LocalDate.now();

		return getMoon(location, id, now);
	}

	@GetMapping(value = "/future")
	public List<Moon> future(@RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") String tz,
			@RequestParam(value = "amount", defaultValue = "7") int amount,
			@RequestParam(value = "interval", defaultValue = "0") Interval interval,
			@RequestParam(value = "location", defaultValue = "52.379189, 4.899431") Location location) {

		TZID id = () -> tz;
		List<Moon> moons = new ArrayList<>();

		switch (interval) {
		case DAY:
			for (int i = 0; i < amount; ++i) {
				LocalDate now = LocalDate.now().plusDays(i);
				Moon moon = getMoon(location, id, now);

				moons.add(moon);
			}
			break;
		case MONTH:
			for (int i = 0; i < amount; ++i) {
				LocalDate now = LocalDate.now().plusMonths(i);
				Moon moon = getMoon(location, id, now);

				moons.add(moon);
			}
			break;
		case YEAR:
			for (int i = 0; i < amount; ++i) {
				LocalDate now = LocalDate.now().plusYears(i);

				Moon moon = getMoon(location, id, now);
				moons.add(moon);
			}
			break;
		default:
			break;
		}

		return moons;

	}

	@GetMapping(value = "/past")
	public List<Moon> past(@RequestParam(value = "tz", defaultValue = "Europe/Amsterdam") String tz,
			@RequestParam(value = "amount", defaultValue = "7") int amount,
			@RequestParam(value = "interval", defaultValue = "0") Interval interval,
			@RequestParam(value = "location", defaultValue = "52.379189, 4.899431") Location location) {

		TZID id = () -> tz;
		List<Moon> moons = new ArrayList<>();

		switch (interval) {
		case DAY:
			for (int i = 0; i < amount; ++i) {
				LocalDate now = LocalDate.now().minusDays(i);

				Moon moon = getMoon(location, id, now);
				moons.add(moon);
			}
			break;
		case MONTH:
			for (int i = 0; i < amount; ++i) {
				LocalDate now = LocalDate.now().minusMonths(i);

				Moon moon = getMoon(location, id, now);
				moons.add(moon);
			}
			break;
		case YEAR:
			for (int i = 0; i < amount; ++i) {
				LocalDate now = LocalDate.now().minusYears(i);
				Moon moon = getMoon(location, id, now);

				moons.add(moon);
			}
			break;
		default:
			break;
		}

		return moons;

	}

	private Moon getMoon(Location location, TZID id, LocalDate now) {
		PlainDate plainDate = PlainDate.from(now);
		return new Moon(now, moonService.getMoonrise(id, plainDate, location),
				moonService.getMoonSet(id, plainDate, location), id, location);

	}

}
