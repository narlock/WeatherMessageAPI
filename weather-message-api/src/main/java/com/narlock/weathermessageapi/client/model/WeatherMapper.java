package com.narlock.weathermessageapi.client.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.narlock.weathermessageapi.domain.WeatherInformation;

public class WeatherMapper {

    public static Weather mapWeatherFromJsonString(String openWeatherApiResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(openWeatherApiResponse, Weather.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static WeatherInformation constructWeatherInformationFromWeather(Weather weather) {
        return WeatherInformation.builder()
                .city(weather.getName())
                .countryCode(weather.getSys().getCountry())
                .temp((int) Math.floor(weather.getMain().getTemp()))
                .description(weather.getWeather()[0].getMain())
                .build();
    }
}
