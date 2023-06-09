package com.narlock.weathermessageapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherMessageResponse {
    private WeatherInformation weatherInformation;
    private String status;
    private String message;
}
