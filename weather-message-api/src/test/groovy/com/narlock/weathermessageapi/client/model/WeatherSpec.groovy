package com.narlock.weathermessageapi.client.model

import spock.lang.Specification

class WeatherSpec extends Specification {

    def "Weather class should have properties"() {
        given:
        Weather weather = new Weather()

        expect:
        weather.coord == null
        weather.weather == null
        weather.base == null
        weather.main == null
        weather.visibility == 0
        weather.wind == null
        weather.clouds == null
        weather.dt == 0
        weather.sys == null
        weather.timezone == 0
        weather.id == 0
        weather.name == null
        weather.cod == 0
    }

    def "Coord class should have properties"() {
        given:
        Weather.Coord coord = new Weather.Coord()

        expect:
        coord.lon == 0.0f
        coord.lat == 0.0f
    }

    def "WeatherData class should have properties"() {
        given:
        Weather.WeatherData weatherData = new Weather.WeatherData()

        expect:
        weatherData.id == 0
        weatherData.main == null
        weatherData.description == null
        weatherData.icon == null
    }

    def "MainData class should have properties"() {
        given:
        Weather.MainData mainData = new Weather.MainData()

        expect:
        mainData.temp == 0.0f
        mainData.feels_like == 0.0f
        mainData.temp_min == 0.0f
        mainData.temp_max == 0.0f
        mainData.pressure == 0
        mainData.humidity == 0
    }

    def "WindData class should have properties"() {
        given:
        Weather.WindData windData = new Weather.WindData()

        expect:
        windData.speed == 0.0f
        windData.deg == 0
    }

    def "CloudsData class should have properties"() {
        given:
        Weather.CloudsData cloudsData = new Weather.CloudsData()

        expect:
        cloudsData.all == 0
    }

    def "SysData class should have properties"() {
        given:
        Weather.SysData sysData = new Weather.SysData()

        expect:
        sysData.type == 0
        sysData.id == 0
        sysData.country == null
        sysData.sunrise == 0
        sysData.sunset == 0
    }
}
