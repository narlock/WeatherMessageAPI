package com.narlock.weathermessageapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.narlock.weathermessageapi.client.model.Weather;
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

        Weather weather = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            weather = objectMapper.readValue(apiResponse, Weather.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        // Construct WeatherInformation Object
        return WeatherInformation.builder()
                .city(weather.getName())
                .countryCode(weather.getSys().getCountry())
                .temp((int) Math.floor(weather.getMain().getTemp()))
                .description(weather.getWeather()[0].getMain())
                .build();
    }
}
