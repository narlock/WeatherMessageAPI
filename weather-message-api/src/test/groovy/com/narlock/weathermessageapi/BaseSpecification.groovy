package com.narlock.weathermessageapi

import com.narlock.weathermessageapi.client.model.Weather
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
                .weatherInformation(validWeatherInformation())
                .build();
    }

    WeatherInformation validWeatherInformation() {
        return WeatherInformation.builder()
                .city("New York")
                .countryCode("US")
                .temp(42)
                .unit("F")
                .description("Clear")
                .build();
    }

    String openWeatherResponse() {
        return '{"coord":{"lon":-74.006,"lat":40.7143},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"base":"stations","main":{"temp":293.48,"feels_like":293.24,"temp_min":289.91,"temp_max":295.34,"pressure":1016,"humidity":64},"visibility":10000,"wind":{"speed":3.09,"deg":250},"clouds":{"all":0},"dt":1683976067,"sys":{"type":2,"id":2008101,"country":"US","sunrise":1683970873,"sunset":1684022618},"timezone":-14400,"id":5128581,"name":"New York","cod":200}';
    }

    String openWeatherResponseUnknownProperties() {
        return '{"coord":{"lon":-74.006,"lat":40.7143},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"base":"stations","main":{"temp":293.48,"feels_like":293.24,"temp_min":289.91,"temp_max":295.34,"pressure":1016,"humidity":64},"visibility":10000,"wind":{"speed":3.09,"deg":250},"clouds":{"all":0},"dt":1683976067,"sys":{"type":2,"id":2008101,"country":"US","sunrise":1683970873,"sunset":1684022618},"timezone":-14400,"id":5128581,"name":"New York","cod":200,"unknown":"hello"}';
    }

    Weather validWeather() {
        Weather weather = new Weather()

        weather.coord = new Weather.Coord()
        weather.coord.lat = 40.7143f
        weather.coord.lon = -74.006f

        weather.weather = [new Weather.WeatherData(
                id: 800,
                main: 'Clear',
                description: 'clear sky',
                icon: '01d'
        )]

        weather.base = 'stations'

        weather.main = new Weather.MainData(
                temp: 293.48f,
                feels_like: 293.24f,
                temp_min: 289.91f,
                temp_max: 295.34f,
                pressure: 1016,
                humidity: 64
        )

        weather.visibility = 10000

        weather.wind = new Weather.WindData(
                speed: 3.09f,
                deg: 250
        )

        weather.clouds = new Weather.CloudsData(
                all: 0
        )

        weather.dt = 1683976067

        weather.sys = new Weather.SysData(
                type: 2,
                id: 2008101,
                country: 'US',
                sunrise: 1683970873,
                sunset: 1684022618
        )

        weather.timezone = -14400
        weather.id = 5128581
        weather.name = 'New York'
        weather.cod = 200

        return weather
    }

}
