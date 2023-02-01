package com.weather.controller;

import com.weather.model.Weather;
import com.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    //Today's weather for the location
    @GetMapping("/{location}")
    public ResponseEntity<Weather> getWeatherLocation(@PathVariable String location) {
        return ResponseEntity.ok(weatherService.getWeatherLocation(location));
    }

    //Today's weather for the location with temperature type (Fahrenheit - Celsius)
    @GetMapping("/{location}/{temperatureType}")
    public ResponseEntity<Weather> getWeatherLocationWithTemp(@PathVariable String location, @PathVariable String temperatureType) {
        return ResponseEntity.ok(weatherService.getWeatherLocationWithTemp(location, temperatureType));
    }

    //(Day - Week - Month) weather for the location
    @GetMapping("/byTime/{location}/{time}")
    public ResponseEntity<Weather> getWeatherAndTime(@PathVariable String location, @PathVariable String time) {
        return ResponseEntity.ok(weatherService.getWeatherLocationAndTime(location, time));
    }

    //(Day - Week - Month) weather for the location with temperature type (Fahrenheit - Celsius)
    @GetMapping("/byTime/{location}/{time}/{temperatureType}")
    public ResponseEntity<Weather> getWeatherLocationAndTimeWithTemp(@PathVariable String location, @PathVariable String time, @PathVariable String temperatureType) {
        return ResponseEntity.ok(weatherService.getWeatherLocationAndTimeWithTemp(location, time, temperatureType));
    }
}
