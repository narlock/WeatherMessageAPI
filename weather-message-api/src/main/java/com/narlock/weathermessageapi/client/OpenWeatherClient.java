package com.narlock.weathermessageapi.client;

import com.narlock.weathermessageapi.domain.WeatherInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherClient {

    @Value("${openweather.apiKey}")
    private String apiKey;

    public WeatherInformation getWeatherInformation(String city, String countryCode) {
        // Make API Call
        
        // Construct WeatherInformation Object
        return WeatherInformation.builder()
                .city("New York")
                .countryCode("US")
                .temp(30)
                .description("Cloudy")
                .build();
    }
}
