package com.weather.config;

import com.weather.model.Day;
import com.weather.model.enumeration.TemperatureType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeatherProcess {

    //Date format for URI
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    //API source address
    public static final String weatherUriPath = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    //Temperature Type (Fahrenheit - Celsius)
    public static final String unitGroup = "?unitGroup=";

    //API Key
    public static final String weatherKey = "&key=EGNSBYAUGW6NCW5UJY6YSTLS5";

    //Return json content type
    public static final String contentType = "&contentType=json";

    //Calculate day-week-month
    public static String calculateTime(String time) {
        Calendar cal = Calendar.getInstance();
        Calendar futureCal = Calendar.getInstance();
        if (time.equals("week")) {
            futureCal.add(Calendar.DAY_OF_MONTH, 6);
            return ("/" + format.format(cal.getTime()) + "/" + format.format(futureCal.getTime()));
        } else if (time.equals("month")) {
            futureCal.add(Calendar.MONTH, 1);
            return ("/" + format.format(cal.getTime()) + "/" + format.format(futureCal.getTime()));
        }
        return ("/" + format.format(cal.getTime()) + "/" + format.format(cal.getTime()));
    }

    //Today weather
    public static String calculateTime() {
        Calendar cal = Calendar.getInstance();
        return ("/" + format.format(cal.getTime()) + "/" + format.format(cal.getTime()));
    }

    //Temperature Type (Fahrenheit - Celsius)
    public static String calculateTemperatureType(String temperatureType) {
        if (temperatureType.equalsIgnoreCase("C") ||
                temperatureType.equalsIgnoreCase("Celsius")) {
            return TemperatureType.Celsius.toString();
        } else if (temperatureType.equalsIgnoreCase("F") ||
                temperatureType.equalsIgnoreCase("Fahrenheit")) {
            return TemperatureType.Fahrenheit.toString();
        }
        return TemperatureType.Celsius.toString();
    }

    //Weather image for Thymeleaf page
    public static List<Day> imageProcess(List<Day> days) {
        List<Day> dayList = new ArrayList<>();
        for (Day d : days) {
            if (d.getConditions().contains("Rain")) {
                d.setPath("https://cdn-icons-png.flaticon.com/512/7038/7038436.png");
            } else if (d.getConditions().contains("Partially cloudy")) {
                d.setPath("https://cdn-icons-png.flaticon.com/512/1146/1146869.png");
            } else if (d.getConditions().contains("Snow")) {
                d.setPath("https://cdn-icons-png.flaticon.com/512/7038/7038368.png");
            } else if (d.getConditions().contains("Overcast")) {
                d.setPath("https://cdn-icons-png.flaticon.com/512/5546/5546241.png");
            } else if (d.getConditions().contains("Clear")) {
                d.setPath("https://cdn-icons-png.flaticon.com/512/6974/6974833.png");
            } else {
                d.setPath("https://cdn-icons-png.flaticon.com/512/984/984622.png");
            }
            dayList.add(d);
        }
        return dayList;
    }

    //Weather temperature type for Thymeleaf page (°C - °F)
    public static List<Day> tempType(List<Day> days, String temperatureType) {
        List<Day> dayList = new ArrayList<>();
        for (Day d : days) {
            if (temperatureType.equalsIgnoreCase("c") ||
                    temperatureType.equalsIgnoreCase("celsius")) {
                d.setTemperatureType("°C");
            } else if (temperatureType.equalsIgnoreCase("f") ||
                    temperatureType.equalsIgnoreCase("fahrenheit")) {
                d.setTemperatureType("°F");
            } else {
                d.setTemperatureType("°C");
            }
            dayList.add(d);
        }
        return dayList;
    }
}
