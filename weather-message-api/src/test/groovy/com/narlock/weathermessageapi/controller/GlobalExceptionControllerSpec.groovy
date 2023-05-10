package com.narlock.weathermessageapi.controller

import com.narlock.weathermessageapi.BaseSpecification
import com.narlock.weathermessageapi.domain.TwilioClientException
import org.springframework.http.HttpStatusCode
import org.springframework.http.converter.HttpMessageNotReadableException

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
        def result = globalExceptionController.handleTwilioClientException(new TwilioClientException("test"))

        then:
        result
        result.statusCode == HttpStatusCode.valueOf(500)
        result.body.errorMessage == "test"
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
