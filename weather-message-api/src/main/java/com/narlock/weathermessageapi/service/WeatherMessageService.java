package com.narlock.weathermessageapi.service;

import com.narlock.weathermessageapi.client.OpenWeatherClient;
import com.narlock.weathermessageapi.client.TwilioClient;
import com.narlock.weathermessageapi.domain.WeatherMessageResponse;
import com.narlock.weathermessageapi.domain.WeatherInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeatherMessageService {

    @Autowired
    private OpenWeatherClient openWeatherClient;

    @Autowired
    private TwilioClient twilioClient;

    public WeatherMessageResponse getSendWeatherMessageSMS(String city,
                                                           String countryCode,
                                                           String phone
    ) {
        // Get Weather Information from Open Weather API
        WeatherInformation weatherInformation = openWeatherClient.getWeatherInformation(city, countryCode);

        // Construct Message To Send
        String messageBody = constructWeatherMessageFromInformation(weatherInformation);

        // Send sms to each phone number of Weather Message
        // String twilioStatus = twilioClient.sendSms(phone, messageBody);
        String twilioStatus = "QUEUED"; // Debug message for less API calls

        // Return Response Description
        log.info("Constructing WeatherMessageResponse");
        return WeatherMessageResponse.builder()
                .weatherInformation(weatherInformation)
                .status(twilioStatus.toUpperCase())
                .message(messageBody)
                .build();
    }

    public WeatherMessageResponse getSendWeatherMessageVoice(String city,
                                                           String countryCode,
                                                           String phone
    ) {
        // Get Weather Information from Open Weather API
        WeatherInformation weatherInformation = openWeatherClient.getWeatherInformation(city, countryCode);

        // Construct Message To Send
        String messageBody = constructWeatherMessageFromInformation(weatherInformation);

        // Send sms to each phone number of Weather Message
        // String twilioStatus = twilioClient.sendVoice(phone, messageBody);
        String twilioStatus = "QUEUED"; // Debug message for less API calls

        // Return Response Description
        log.info("Constructing WeatherMessageResponse");
        return WeatherMessageResponse.builder()
                .weatherInformation(weatherInformation)
                .status(twilioStatus.toUpperCase())
                .message(messageBody)
                .build();
    }

    public String constructWeatherMessageFromInformation(WeatherInformation weatherInformation) {
        return "Hello there, currently it's " +
                weatherInformation.getDescription().toLowerCase() +
                " with a temperature of " +
                weatherInformation.getTemp() +
                " degrees in " +
                weatherInformation.getCity() +
                ", " +
                weatherInformation.getCountryCode() +
                "!";
    }
}
