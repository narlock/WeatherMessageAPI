package com.narlock.weathermessageapi.client

import com.narlock.weathermessageapi.BaseSpecification
import com.narlock.weathermessageapi.domain.WeatherInformation
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

class OpenWeatherClientSpec extends BaseSpecification {

    WebClient webClient = Mock()
    OpenWeatherClient openWeatherClient = new OpenWeatherClient(webClient)

    def "test get weather information success"() {
        given:
        def mockRequestHeadersUriSpec = Mock(RequestHeadersUriSpec.class)
        def mockUri = Mock(WebClient.RequestHeadersSpec.class)
        def mockResponseSpec = Mock(WebClient.ResponseSpec.class)
        def mockBodyToMono = Mock(Mono.class)

        when:
        def result = openWeatherClient.getWeatherInformation(city, countryCode)

        then:
        1 * webClient.get() >> mockRequestHeadersUriSpec
        1 * mockRequestHeadersUriSpec.uri(_) >> mockUri
        1 * mockUri.retrieve() >> mockResponseSpec
        1 * mockResponseSpec.bodyToMono(String.class) >> mockBodyToMono
        1 * mockBodyToMono.block() >> apiResponse

        result == expected

        where:
        city       | countryCode | apiResponse           || expected
        "New York" | "US"        | openWeatherResponse() || WeatherInformation.builder().city("New York").countryCode("US").temp(293).description("Clear").build()
    }


    def "test get weather information failure"() {
        given:
        def mockRequestHeadersUriSpec = Mock(RequestHeadersUriSpec.class)
        def mockUri = Mock(WebClient.RequestHeadersSpec.class)
        def mockResponseSpec = Mock(WebClient.ResponseSpec.class)
        def mockBodyToMono = Mock(Mono.class)

        when:
        openWeatherClient.getWeatherInformation("test", "test")

        then:
        1 * webClient.get() >> mockRequestHeadersUriSpec
        1 * mockRequestHeadersUriSpec.uri(_) >> mockUri
        1 * mockUri.retrieve() >> mockResponseSpec
        1 * mockResponseSpec.bodyToMono(String.class) >> mockBodyToMono
        1 * mockBodyToMono.block() >> { throw WebClientResponseException }

        thrown Exception
    }

}
