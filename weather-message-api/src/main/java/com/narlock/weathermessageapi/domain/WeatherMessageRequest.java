package com.narlock.weathermessageapi.domain;

import lombok.*;

@Data
@Builder
public class WeatherMessageRequest {

    @NonNull
    private String weatherCity;

    @NonNull
    private String weatherCountryCode;

    @NonNull
    private String contact;

    @NonNull
    private String unit;
}
