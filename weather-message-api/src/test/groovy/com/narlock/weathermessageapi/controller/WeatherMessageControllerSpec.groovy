package com.narlock.weathermessageapi.controller

import com.narlock.weathermessageapi.BaseSpecification
import com.narlock.weathermessageapi.service.WeatherMessageService
import org.springframework.http.HttpStatusCode

class WeatherMessageControllerSpec extends BaseSpecification {

    WeatherMessageService weatherMessageService = Mock()
    WeatherMessageController weatherMessageController = new WeatherMessageController(weatherMessageService)

    def "should send #weatherType message"() {
        given:
        def request = validMessageRequest()
        def response = validMessageResponse()

        when:
        def result = weatherMessageController.sendWeatherTextMessage(request)

        then:
        1 * weatherMessageService.getSendWeatherMessageSMS("New York", "US", "1234567890", "F") >> response
        result.statusCode == HttpStatusCode.valueOf(200)
        result.body.message == "Test Message"
        result.body.status == "QUEUED"
        result.body.weatherInformation.city == "New York"
        result.body.weatherInformation.countryCode == "US"
        result.body.weatherInformation.unit == "F"
    }

}
