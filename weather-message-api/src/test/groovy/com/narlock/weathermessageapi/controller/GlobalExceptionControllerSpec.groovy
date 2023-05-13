package com.narlock.weathermessageapi.controller

import com.narlock.weathermessageapi.BaseSpecification
import com.narlock.weathermessageapi.domain.TwilioClientException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.reactive.function.client.WebClientResponseException

import java.nio.charset.Charset

class GlobalExceptionControllerSpec extends BaseSpecification {
    GlobalExceptionController globalExceptionController = new GlobalExceptionController()

    def "test bad request response entity for HttpMessageNotReadableException"() {
        when:
        def result = globalExceptionController.handleBadRequestException(new HttpMessageNotReadableException("test"))

        then:
        result
        result.statusCode == HttpStatusCode.valueOf(400)
        result.body.errorMessage == "Request fields are invalid"
    }

    def "test bad request response entity for IllegalArgumentException"() {
        when:
        def result = globalExceptionController.handleBadRequestException(new IllegalArgumentException("test"))

        then:
        result
        result.statusCode == HttpStatusCode.valueOf(400)
        result.body.errorMessage == "Request fields are invalid"
    }

    def "test twilio client response entity for TwilioClientException"() {
        when:
        def result = globalExceptionController.handleClientException(new TwilioClientException("test"))

        then:
        result
        result.statusCode == HttpStatusCode.valueOf(500)
        result.body.errorMessage == "test"
    }

    def "test twilio client response entity for WebClientResponseException"() {
        when:
        def statusCode = 500
        def statusText = "Internal Server Error"
        def headers = new HttpHeaders()
        def responseBody = null
        def charset = null

        def exception = new WebClientResponseException(statusCode, statusText, headers, responseBody, charset)

        def result = globalExceptionController.handleClientException(exception)

        then:
        result
        result.statusCode == HttpStatusCode.valueOf(500)
        result.body.errorMessage == "500 Internal Server Error"
    }

    def "test catch all response entity"() {
        when:
        def result = globalExceptionController.catchAll(new Exception("test"))

        then:
        result
        result.statusCode == HttpStatusCode.valueOf(500)
        result.body.errorMessage == "test"
    }
}
