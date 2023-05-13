package com.narlock.weathermessageapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.narlock.weathermessageapi.client.model.Weather;
import com.narlock.weathermessageapi.client.model.WeatherMapper;
import com.narlock.weathermessageapi.domain.WeatherInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class OpenWeatherClient {

    @Autowired
    private final WebClient webClient;

    @Value("${openweather.apiKey}")
    private String apiKey;

    public OpenWeatherClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public WeatherInformation getWeatherInformation(String city, String countryCode) {
        // Make API Call

        String apiResponse = null;
        try {
            apiResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/weather")
                            .queryParam("q", city + "," + countryCode)
                            .queryParam("appid", "{apiKey}")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw e;
        }

        Weather weather = WeatherMapper.mapWeatherFromJsonString(apiResponse);
        return WeatherMapper.constructWeatherInformationFromWeather(weather);
    }
}
