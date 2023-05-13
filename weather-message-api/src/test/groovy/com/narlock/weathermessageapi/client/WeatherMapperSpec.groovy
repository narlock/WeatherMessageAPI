package com.narlock.weathermessageapi.client

import com.narlock.weathermessageapi.BaseSpecification
import com.narlock.weathermessageapi.client.model.WeatherMapper

class WeatherMapperSpec extends BaseSpecification {

    def "test map weather from json string success"() {
        given:
        def openWeatherApiResponse = openWeatherResponse()

        when:
        def weather = WeatherMapper.mapWeatherFromJsonString(openWeatherApiResponse)

        then:
        weather

        weather.coord.lat == 40.7143 as Float
        weather.coord.lon == -74.006 as Float

        weather.weather[0].id == 800
        weather.weather[0].main == 'Clear'
        weather.weather[0].description == 'clear sky'
        weather.weather[0].icon == '01d'

        weather.base == 'stations'

        weather.main.temp == 293.48 as Float
        weather.main.feels_like == 293.24 as Float
        weather.main.temp_min == 289.91 as Float
        weather.main.temp_max == 295.34 as Float
        weather.main.pressure == 1016
        weather.main.humidity == 64

        weather.visibility == 10000

        weather.wind.speed == 3.09 as Float
        weather.wind.deg == 250

        weather.clouds.all == 0

        weather.dt == 1683976067

        weather.sys.type == 2
        weather.sys.id == 2008101
        weather.sys.country == 'US'
        weather.sys.sunrise == 1683970873
        weather.sys.sunset == 1684022618

        weather.timezone == -14400
        weather.id == 5128581
        weather.name == 'New York'
        weather.cod == 200
    }

    def "test map weather from json string invalid json"() {
        given:
        def openWeatherApiResponse = null

        when:
        WeatherMapper.mapWeatherFromJsonString(openWeatherApiResponse)

        then:
        thrown IllegalArgumentException
    }

    def "test construct weather information from weather success"() {
        given:
        def weather = validWeather()

        when:
        def weatherInformation = WeatherMapper.constructWeatherInformationFromWeather(weather)

        then:
        weatherInformation

        weatherInformation.description == 'Clear'
        weatherInformation.countryCode == 'US'
        weatherInformation.city == 'New York'
        weatherInformation.temp == 293
    }

}
