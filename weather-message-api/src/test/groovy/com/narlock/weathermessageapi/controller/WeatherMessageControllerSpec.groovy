package com.narlock.weathermessageapi.controller

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import com.narlock.weathermessageapi.service.WeatherMessageService

class WeatherMessageControllerSpec extends Specification {

    MockMvc mockMvc

    def weatherMessageService = Mock(WeatherMessageService)

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new WeatherMessageController(weatherMessageService)).build()
    }

    def "should return 'Hello World'"() {
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/v1/hello"))

        then:
        response.andExpect(MockMvcResultMatchers.status().isOk())
        response.andExpect(MockMvcResultMatchers.content().string("Hello World"))
    }
}
