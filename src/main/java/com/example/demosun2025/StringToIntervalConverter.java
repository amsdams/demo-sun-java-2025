package com.example.demosun2025;

import com.example.demosun2025.model.Interval;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class StringToIntervalConverter implements Converter<String, Interval> {
 
    @Override
    public Interval convert(@Nullable String index) {
   

        return Interval.valueOf(index);
    }
}