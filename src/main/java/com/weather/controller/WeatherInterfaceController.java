package com.weather.controller;

import com.weather.config.WeatherProcess;
import com.weather.model.Day;
import com.weather.model.Weather;
import com.weather.service.WeatherInterfaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/v1/weather/interface")
public class WeatherInterfaceController {

    private final WeatherInterfaceService weatherInterfaceService;

    public WeatherInterfaceController(WeatherInterfaceService weatherInterfaceService) {
        this.weatherInterfaceService = weatherInterfaceService;
    }

    //Today's weather for the location for Thymeleaf Page
    @GetMapping("/{location}")
    public ModelAndView getWeatherLocation(@PathVariable String location) {
        ModelAndView mav = new ModelAndView("weather-list");
        Weather weather = weatherInterfaceService.getWeatherLocation(location);
        List<Day> days = weather.getDays();
        days = WeatherProcess.imageProcess(days);
        days = WeatherProcess.tempType(days, "celsius");
        mav.addObject("weather", weather);
        mav.addObject("days", days);
        return mav;
    }

    //Today's weather for the location with temperature type (Fahrenheit - Celsius) for Thymeleaf Page
    @GetMapping("/{location}/{temperatureType}")
    public ModelAndView getWeatherLocationWithTemp(@PathVariable String location, @PathVariable String temperatureType) {
        ModelAndView mav = new ModelAndView("weather-list");
        Weather weather = weatherInterfaceService.getWeatherLocationWithTemp(location, temperatureType);
        List<Day> days = weather.getDays();
        days = WeatherProcess.imageProcess(days);
        days = WeatherProcess.tempType(days, "celsius");
        mav.addObject("weather", weather);
        mav.addObject("days", days);
        return mav;
    }

    //(Day - Week - Month) weather for the location for Thymeleaf Page
    @GetMapping("/byTime/{location}/{time}")
    public ModelAndView getWeatherLocationAndTime(@PathVariable String location, @PathVariable String time) {
        ModelAndView mav = new ModelAndView("weather-list");
        Weather weather = weatherInterfaceService.getWeatherLocationAndTime(location, time);
        List<Day> days = weather.getDays();
        days = WeatherProcess.imageProcess(days);
        days = WeatherProcess.tempType(days, "celsius");
        mav.addObject("weather", weather);
        mav.addObject("days", days);
        return mav;
    }

    //(Day - Week - Month) weather for the location with temperature type (Fahrenheit - Celsius) for Thymeleaf Page
    @GetMapping("/byTime/{location}/{time}/{temperatureType}")
    public ModelAndView getWeatherLocationAndTimeWithTemp(@PathVariable String location, @PathVariable String time, @PathVariable String temperatureType) {
        ModelAndView mav = new ModelAndView("weather-list");
        Weather weather = weatherInterfaceService.getWeatherLocationAndTimeWithTemp(location, time, temperatureType);
        List<Day> days = weather.getDays();
        days = WeatherProcess.imageProcess(days);
        days = WeatherProcess.tempType(days, temperatureType);
        mav.addObject("weather", weather);
        mav.addObject("days", days);
        return mav;
    }
}
