package com.example.demosun2025;

import com.example.demosun2025.model.Location;
import org.springframework.core.convert.converter.Converter;

public class StringToLocationConverter implements Converter<String, Location> {
 
    @Override
    public Location convert(String source) {
        Location l = new Location();
        String [] latlong = source.split(",");
        l.setLatitude(Double.parseDouble(latlong[0]));
        l.setLongitude(Double.parseDouble(latlong[1]));

        return l;
    }
}