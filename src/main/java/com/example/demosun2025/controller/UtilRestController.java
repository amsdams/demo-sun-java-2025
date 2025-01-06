package com.example.demosun2025.controller;

import lombok.extern.slf4j.Slf4j;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("util")
public class UtilRestController {
    @GetMapping(value = "/timezones")
    public List<String> timezones() {

        List<TZID> ids = Timezone.getAvailableIDs();

        return ids.stream().map(TZID::canonical).collect(Collectors.toList());
    }
}
