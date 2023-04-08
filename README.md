# WeatherMessageAPI

**Anthony Narlock**

This project aims to build a Spring-based RESTful API that delivers weather information through text SMS messaging and phone calls.

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Groovy](https://img.shields.io/badge/Groovy-4298B8.svg?style=for-the-badge&logo=Apache+Groovy&logoColor=white)

## Context

<p align="center">
  <img src="./README%20Assets/ContextDiagram.png" width="600px"/>
</p>

## API Specification

### `POST weather/v1/sms`
Sends a message to an array of phone numbers with the current weather information for a specific location.

**Request**
```json
GET /weather/v1/sms

{
    "weather": {
        "city": "New York",
        "countryCode": "US"
    },
    "contact": [
        "111-222-3333",
        "444-555-6666",
        "777-888-9999"
    ]
}
```

**Response**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "weatherInformation": {
        "city": "New York",
        "countryCode": "US",
        "temp": 20,
        "description": "Cloudy",
    },
    "sms": "Hello there, currently it's cloudy with a temperature of 20 degrees in New York!"
}
```

### `POST weather/v1/voice`
Makes a voice call to an array of phone numbers and delivers the current weather information for a specific location.

**Request**
```json
GET /weather/v1/voice

{
    "weather": {
        "city": "New York",
        "countryCode": "US"
    },
    "contact": [
        "111-222-3333",
        "444-555-6666",
        "777-888-9999"
    ]
}
```

**Response**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "weatherInformation": {
        "city": "New York",
        "countryCode": "US",
        "temp": 20,
        "description": "Cloudy",
    },
    "sms": "Hello there, currently it's cloudy with a temperature of 20 degrees in New York!"
}
```

## Developer Environment Dependencies
- [OpenWeather API](https://openweathermap.org/api) - Weather data API to retrieve weather information.
- [Twilio](https://www.twilio.com/docs/usage/api) - A cloud communications platform for sending SMS and making phone calls.
