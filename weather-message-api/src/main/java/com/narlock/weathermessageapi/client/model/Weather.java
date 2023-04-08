package com.narlock.weathermessageapi.client.model;

import lombok.Data;

@Data
public class Weather {
    private Coord coord;
    private WeatherData[] weather;
    private String base;
    private MainData main;
    private int visibility;
    private WindData wind;
    private CloudsData clouds;
    private long dt;
    private SysData sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    @Data
    public static class Coord {
        private float lon;
        private float lat;
    }

    @Data
    public static class WeatherData {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class MainData {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private int pressure;
        private int humidity;
    }

    @Data
    public static class WindData {
        private float speed;
        private int deg;
    }

    @Data
    public static class CloudsData {
        private int all;
    }

    @Data
    public static class SysData {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}
