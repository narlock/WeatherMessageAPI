package com.narlock.weathermessageapi.client

import com.narlock.weathermessageapi.BaseSpecification
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

class OpenWeatherClientSpec extends BaseSpecification {

    WebClient webClient = Mock()
    OpenWeatherClient openWeatherClient = new OpenWeatherClient(webClient)

//    def "test get weather information success"() {
//        when:
//        def result = openWeatherClient.getWeatherInformation(city, countryCode)
//
//        then:
//        1 * webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/weather")
//                        .queryParam("q", "$city,$countryCode")
//                        .queryParam("appid", openWeatherClient.apiKey)
//                        .build())
//                .retrieve().bodyToMono(String.class).block() >> apiResponse
//        result == expected
//
//        where:
//        city | countryCode | apiResponse || expected
//        "New York" | "US" | "test" || "test"
//    }

//    def "test get weather information failure"() {
//        when:
//        WebClientResponseException ex
//        try {
//            openWeatherClient.getWeatherInformation("invalid city", "invalid country")
//        } catch (WebClientResponseException e) {
//            ex = e
//        }
//
//        then:
//        ex.statusCode == 404
//    }

}
