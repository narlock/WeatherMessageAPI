package com.narlock.weathermessageapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class WeatherInformation {
    private String city;
    private String countryCode;
    private Integer temp;
    private String unit;
    private String description;
}
