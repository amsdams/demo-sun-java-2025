package com.example.demosun2025;

import net.time4j.tz.TZID;
import org.springframework.core.convert.converter.Converter;

public class TZIDToStringConverter implements Converter<TZID, String> {
	 
    @Override
    public String convert(TZID tzid) {

        return tzid.canonical();
    }
}