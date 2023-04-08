package com.narlock.weathermessageapi.controller;

import com.narlock.weathermessageapi.service.WeatherMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class WeatherMessageController {

    private WeatherMessageService weatherMessageService;

    @Autowired
    public WeatherMessageController(WeatherMessageService weatherMessageService) {
        this.weatherMessageService = weatherMessageService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World");
    }

}
