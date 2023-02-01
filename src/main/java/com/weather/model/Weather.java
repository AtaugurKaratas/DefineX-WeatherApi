package com.weather.model;

import lombok.Data;

import java.util.List;

@Data
public class Weather {
    private int queryCost;

    private double latitude;

    private double longitude;

    private String resolvedAddress;

    private String address;

    private String timezone;

    private int tzoffset;

    private String description;

    private List<Day> days;
}
