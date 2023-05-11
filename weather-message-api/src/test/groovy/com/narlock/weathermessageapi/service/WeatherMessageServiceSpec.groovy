package com.narlock.weathermessageapi.service

import com.narlock.weathermessageapi.BaseSpecification
import com.narlock.weathermessageapi.client.OpenWeatherClient
import com.narlock.weathermessageapi.client.TwilioClient
import com.narlock.weathermessageapi.domain.WeatherInformation
import com.narlock.weathermessageapi.domain.WeatherMessageResponse
import spock.lang.Unroll

class WeatherMessageServiceSpec extends BaseSpecification {

    OpenWeatherClient openWeatherClient = Mock()
    TwilioClient twilioClient = Mock()
    WeatherMessageService weatherMessageService = new WeatherMessageService(
            openWeatherClient,
            twilioClient
    )

    def "test get send weather message sms success"() {
        given:
        def twilioStatus = "queued";

        when:
        WeatherMessageResponse response = weatherMessageService.getSendWeatherMessageSMS("New York", "US", "1112223333", "F")

        then:
        1 * openWeatherClient.getWeatherInformation("New York", "US") >> validWeatherInformation()
        1 * twilioClient.sendSms("1112223333", _) >> twilioStatus

        response
        response.weatherInformation.city == "New York"
        response.weatherInformation.countryCode == "US"
        response.status == "QUEUED"
    }

    def "test get send weather message voice success"() {
        given:
        def twilioStatus = "queued";

        when:
        WeatherMessageResponse response = weatherMessageService.getSendWeatherMessageVoice("New York", "US", "1112223333", "F")

        then:
        1 * openWeatherClient.getWeatherInformation("New York", "US") >> validWeatherInformation()
        1 * twilioClient.sendVoice("1112223333", _) >> twilioStatus

        response
        response.weatherInformation.city == "New York"
        response.weatherInformation.countryCode == "US"
        response.status == "QUEUED"
    }

    @Unroll
    def "test convert temperature based on unit - #unit #expected"() {
        given:
        WeatherInformation weatherInformation = validWeatherInformation()

        when:
        weatherMessageService.convertTemperatureBasedOnUnit(weatherInformation, unit)

        then:
        weatherInformation.temp == expected

        where:
        unit || expected
        "F"  || -384 // Fahrenheit
        "C"  || -231 // Celsius
        "K"  || 42 // Kelvin
    }

    @Unroll
    def "test construct weather message from information - #description #temp #unit #city #countryCode #expected"() {
        given:
        WeatherInformation weatherInformation = WeatherInformation.builder()
                .description(description)
                .temp(temp)
                .unit(unit)
                .city(city)
                .countryCode(countryCode)
                .build()

        when:
        def result = weatherMessageService.constructWeatherMessageFromInformation(weatherInformation)

        then:
        result == expected

        where:
        description | temp | unit | city        | countryCode || expected
        "clear"     | 70   | "F"  | "New York"  | "US"        || "Hello there, currently it's clear with a temperature of 70 degrees F in New York, US!"
        "cloudy"    | 10   | "C"  | "Bucharest" | "RO"        || "Hello there, currently it's cloudy with a temperature of 10 degrees C in Bucharest, RO!"
    }
}
