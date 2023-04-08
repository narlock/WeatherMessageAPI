package com.narlock.weathermessageapi.controller;

import com.narlock.weathermessageapi.domain.RequestDescription;
import com.narlock.weathermessageapi.domain.ResponseDescription;
import com.narlock.weathermessageapi.domain.WeatherInformation;
//import com.narlock.weathermessageapi.service.WeatherMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
public class WeatherMessageController {

//    private WeatherMessageService weatherMessageService;
//
//    @Autowired
//    public WeatherMessageController(WeatherMessageService weatherMessageService) {
//        this.weatherMessageService = weatherMessageService;
//    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World, apiKey");
    }

    @PostMapping("/sms")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDescription> sendWeatherTextMessage(
            @RequestBody RequestDescription requestDescription
    ) {
        log.info("SMS Message Request Received: {}", requestDescription);
//        ResponseDescription description = weatherMessageService.getSendWeatherMessageSMS(
//                requestDescription.getWeatherCity(),
//                requestDescription.getWeatherCountryCode(),
//                requestDescription.getContact()
//        );
        ResponseDescription description = ResponseDescription.builder()
                .message("Hello World")
                .build();
        return ResponseEntity.ok(description);
    }

}
