package com.narlock.weathermessageapi

import com.narlock.weathermessageapi.domain.WeatherInformation
import com.narlock.weathermessageapi.domain.WeatherMessageRequest
import com.narlock.weathermessageapi.domain.WeatherMessageResponse
import spock.lang.Specification

class BaseSpecification extends Specification {

    WeatherMessageRequest validMessageRequest() {
        return WeatherMessageRequest.builder()
                .weatherCity("New York")
                .weatherCountryCode("US")
                .contact("1234567890")
                .unit("F")
                .build();
    }

    WeatherMessageResponse validMessageResponse() {
        return WeatherMessageResponse.builder()
                .message("Test Message")
                .status("QUEUED")
                .weatherInformation(
                        WeatherInformation.builder()
                                .city("New York")
                                .countryCode("US")
                                .temp(42)
                                .unit("F")
                                .description("Clear")
                                .build()
                )
                .build();
    }
}
