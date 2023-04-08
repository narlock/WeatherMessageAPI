package com.narlock.weathermessageapi.service;

import com.narlock.weathermessageapi.client.TwilioClient;
import com.narlock.weathermessageapi.domain.ResponseDescription;
import com.narlock.weathermessageapi.domain.TwilioClientException;
import com.narlock.weathermessageapi.domain.WeatherInformation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherMessageService {

    //    private final OpenWeatherWebClient openWeatherWebClient;
    private final TwilioClient twilioClient;

    public WeatherMessageService(TwilioClient twilioClient) {
        this.twilioClient = twilioClient;
    }

    public ResponseDescription getSendWeatherMessageSMS(String city,
                                                        String countryCode,
                                                        List<String> phones
    ) {
        // Get Weather Information from Open Weather API
        // openWeatherWebClient.getWeatherData(city, countryCode);

        // Construct ResponseDescription Object
        String messageBody = "Hello World";
        ResponseDescription responseDescription = ResponseDescription.builder()
                .weatherInformation(
                        WeatherInformation.builder()
                                .build()
                )
                .message(messageBody)
                .build();

        // Send sms to each phone number of Weather Message
        for (String phone : phones) {
            twilioClient.sendMessage(phone, messageBody);
        }

        // Return Response Description
        return responseDescription;
    }

    public ResponseDescription getSendWeatherMessageVoice() {
        return null;
    }
}
