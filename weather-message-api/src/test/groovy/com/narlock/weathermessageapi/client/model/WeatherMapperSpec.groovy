package com.narlock.weathermessageapi.client.model

import com.narlock.weathermessageapi.domain.WeatherInformation
import spock.lang.Specification

class WeatherMapperSpec extends Specification {

    def "mapWeatherFromJsonString should map JSON response to Weather object"() {
        given:
        String openWeatherApiResponse = """
            {
                "coord": {"lon": 100.0, "lat": 50.0},
                "weather": [{"id": 800, "main": "Clear", "description": "clear sky", "icon": "01d"}],
                "base": "stations",
                "main": {"temp": 22.5, "feels_like": 23.0, "temp_min": 22.0, "temp_max": 23.0, "pressure": 1010, "humidity": 50},
                "visibility": 10000,
                "wind": {"speed": 5.0, "deg": 180},
                "clouds": {"all": 0},
                "dt": 1633182000,
                "sys": {"type": 2, "id": 2019346, "country": "US", "sunrise": 1633153435, "sunset": 1633195320},
                "timezone": -18000,
                "id": 456789,
                "name": "City",
                "cod": 200
            }
        """

        when:
        Weather weather = WeatherMapper.mapWeatherFromJsonString(openWeatherApiResponse)

        then:
        weather.coord.lon == 100.0f
        weather.coord.lat == 50.0f
        weather.weather[0].id == 800
        weather.weather[0].main == "Clear"
        weather.weather[0].description == "clear sky"
        weather.base == "stations"
        weather.main.temp == 22.5f
        weather.main.feels_like == 23.0f
        weather.main.temp_min == 22.0f
        weather.main.temp_max == 23.0f
        weather.main.pressure == 1010
        weather.main.humidity == 50
        weather.visibility == 10000
        weather.wind.speed == 5.0f
        weather.wind.deg == 180
        weather.clouds.all == 0
        weather.dt == 1633182000
        weather.sys.type == 2
        weather.sys.id == 2019346
        weather.sys.country == "US"
        weather.sys.sunrise == 1633153435
        weather.sys.sunset == 1633195320
        weather.timezone == -18000
        weather.id == 456789
        weather.name == "City"
        weather.cod == 200
    }

    def "constructWeatherInformationFromWeather should create WeatherInformation object"() {
        given:
        Weather weather = new Weather()
        weather.setName("City")

        Weather.SysData sysData = new Weather.SysData()
        sysData.setCountry("US")
        weather.setSys(sysData)

        Weather.MainData mainData = new Weather.MainData()
        mainData.setTemp(22.5f)
        weather.setMain(mainData)

        Weather.WeatherData[] weatherDataArray = [new Weather.WeatherData()]
        weatherDataArray[0].setMain("Clear")
        weather.setWeather(weatherDataArray)

        when:
        WeatherInformation weatherInformation = WeatherMapper.constructWeatherInformationFromWeather(weather)

        then:
        weatherInformation.city == "City"
        weatherInformation.countryCode == "US"
        weatherInformation.temp == 22
        weatherInformation.description == "Clear"
    }
}
