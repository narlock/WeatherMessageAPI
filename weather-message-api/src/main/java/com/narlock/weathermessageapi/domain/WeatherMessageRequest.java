package com.narlock.weathermessageapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherMessageRequest {
    private String weatherCity;
    private String weatherCountryCode;
    private String contact;
}