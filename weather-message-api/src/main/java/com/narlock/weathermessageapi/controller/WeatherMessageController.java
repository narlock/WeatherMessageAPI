package com.narlock.weathermessageapi.controller;

import com.narlock.weathermessageapi.domain.WeatherMessageRequest;
import com.narlock.weathermessageapi.domain.WeatherMessageResponse;
import com.narlock.weathermessageapi.service.WeatherMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("weather/api/v1")
public class WeatherMessageController {

    @Autowired
    private WeatherMessageService weatherMessageService;

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World, apiKey");
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
                weatherMessageRequest.getContact()
        );
        return ResponseEntity.ok(response);
    }

}
