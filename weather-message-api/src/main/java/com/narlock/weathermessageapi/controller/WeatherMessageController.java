package com.narlock.weathermessageapi.controller;

import com.narlock.weathermessageapi.domain.WeatherMessageRequest;
import com.narlock.weathermessageapi.domain.WeatherMessageResponse;
import com.narlock.weathermessageapi.service.WeatherMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("weather/api/v1")
public class WeatherMessageController {

    private final WeatherMessageService weatherMessageService;

    public WeatherMessageController(WeatherMessageService weatherMessageService) {
        this.weatherMessageService = weatherMessageService;
    }

    @PostMapping("/sms")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<WeatherMessageResponse> sendWeatherTextMessage(
            @RequestBody WeatherMessageRequest weatherMessageRequest
    ) {
        log.info("SMS Message Request Received: {}", weatherMessageRequest);
        WeatherMessageResponse response = weatherMessageService.getSendWeatherMessageSMS(
                weatherMessageRequest.getWeatherCity(),
                weatherMessageRequest.getWeatherCountryCode(),
                weatherMessageRequest.getContact(),
                weatherMessageRequest.getUnit()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/voice")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<WeatherMessageResponse> sendWeatherVoiceMessage(
            @RequestBody WeatherMessageRequest weatherMessageRequest
    ) {
        log.info("Voice Message Request Received: {}", weatherMessageRequest);
        WeatherMessageResponse response = weatherMessageService.getSendWeatherMessageVoice(
                weatherMessageRequest.getWeatherCity(),
                weatherMessageRequest.getWeatherCountryCode(),
                weatherMessageRequest.getContact(),
                weatherMessageRequest.getUnit()
        );
        return ResponseEntity.ok(response);
    }

}
