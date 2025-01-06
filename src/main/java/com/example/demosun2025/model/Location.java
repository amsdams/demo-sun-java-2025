package com.example.demosun2025.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    Double latitude;
    Double longitude;


    @Override
    public String toString() {
        return latitude +
                ", " + longitude;
    }
}
