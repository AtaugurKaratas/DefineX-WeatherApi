package com.weather.service;

import com.weather.config.WeatherProcess;
import com.weather.exception.LocationNotFoundException;
import com.weather.model.Weather;
import com.weather.model.enumeration.TemperatureType;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    private final HttpHeaders httpHeaders;


    public WeatherService(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    //Today's weather for the location
    public Weather getWeatherLocation(String location) {
        String today = WeatherProcess.calculateTime();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Weather> response = null;
        try {
            response = restTemplate.exchange((WeatherProcess.weatherUriPath + location + today +
                    WeatherProcess.unitGroup + TemperatureType.Celsius + WeatherProcess.weatherKey +
                    WeatherProcess.contentType), HttpMethod.GET, entity, Weather.class);
        } catch (Exception exception) {
            throw new LocationNotFoundException("Location Address is not true");
        }
        return response.getBody();
    }

    //Today's weather for the location with temperature type (Fahrenheit - Celsius)
    public Weather getWeatherLocationWithTemp(String location, String temperatureType) {
        String today = WeatherProcess.calculateTime();
        temperatureType = WeatherProcess.calculateTemperatureType(temperatureType);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Weather> response = null;
        try {
            response = restTemplate.exchange((WeatherProcess.weatherUriPath + location + today +
                    WeatherProcess.unitGroup + temperatureType + WeatherProcess.weatherKey +
                    WeatherProcess.contentType), HttpMethod.GET, entity, Weather.class);
        } catch (Exception exception) {
            throw new LocationNotFoundException("Location Address is not true");
        }
        return response.getBody();
    }

    //(Day - Week - Month) weather for the location
    public Weather getWeatherLocationAndTime(String location, String time) {
        time = WeatherProcess.calculateTime(time);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Weather> response = null;
        try {
            response = restTemplate.exchange((WeatherProcess.weatherUriPath + location + time +
                    WeatherProcess.unitGroup + TemperatureType.Celsius + WeatherProcess.weatherKey +
                    WeatherProcess.contentType), HttpMethod.GET, entity, Weather.class);
        } catch (Exception exception) {
            throw new LocationNotFoundException("Location Address is not true");
        }
        return response.getBody();
    }

    //(Day - Week - Month) weather for the location with temperature type (Fahrenheit - Celsius)
    public Weather getWeatherLocationAndTimeWithTemp(String location, String time, String temperatureType) {
        time = WeatherProcess.calculateTime(time);
        temperatureType = WeatherProcess.calculateTemperatureType(temperatureType);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Weather> response = null;
        try {
            response = restTemplate.exchange((WeatherProcess.weatherUriPath + location + time +
                    WeatherProcess.unitGroup + temperatureType + WeatherProcess.weatherKey +
                    WeatherProcess.contentType), HttpMethod.GET, entity, Weather.class);
        } catch (Exception exception) {
            throw new LocationNotFoundException("Location Address is not true");
        }
        return response.getBody();
    }
}
