package com.narlock.weathermessageapi.config;

import com.narlock.weathermessageapi.client.TwilioClient;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${weather.client.twilio.accountSid}")
    private String twilioAccountSid;

    @Value("${weather.client.twilio.authToken}")
    private String twilioAuthToken;

    @Bean
    public TwilioClient twilioClient() {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        return new TwilioClient();
    }

    @Bean("openweather")
    public WebClient openWeatherWebClient(@Value("${weather.client.openweather.url}") String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }

}

