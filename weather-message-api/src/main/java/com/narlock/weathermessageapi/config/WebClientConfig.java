package com.narlock.weathermessageapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class WebClientConfig {

    @Bean("twilio")
    public WebClient twilioWebClient(@Value("${weather.client.twilio.url}") String url,
                                     @Value("${weather.client.twilio.accountSid}") String accountSid,
                                     @Value("${weather.client.twilio.authToken}") String authToken) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }

    @Bean("openweather")
    public WebClient openWeatherWebClient(@Value("${weather.client.openweather.url}") String url,
                                          @Value("${weather.client.openweather.apiKey}") String apiKey) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }

}

