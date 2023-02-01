package com.weather.model.enumeration;

public enum TemperatureType {
    Fahrenheit{
        @Override
        public String toString() {
            return "us";
        }
    },
    Celsius{
        @Override
        public String toString() {
            return "metric";
        }
    }
}
