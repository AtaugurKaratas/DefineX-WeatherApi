package com.weather.model;

import lombok.Data;

@Data
public class Day {
    private String datetime;

    private double tempmax;

    private double tempmin;

    private double temp;

    private double humanity;

    private String conditions;

    private String description;

    private String path;

    private String temperatureType;
}
