# Weather Message API

**Anthony Narlock**

This project aims to build a Spring-based RESTful API that delivers weather information through text SMS messaging and phone calls.

![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring+boot&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Groovy](https://img.shields.io/badge/Groovy-4298B8.svg?style=for-the-badge&logo=Apache+Groovy&logoColor=white)

## Context

<p align="center">
  <img src="./README%20Assets/ContextDiagram.png" width="600px"/>
</p>

## API Specification

### POST `weather/v1/sms`
Sends a message to an array of phone numbers with the current weather information for a specific location.

**Request**
```json
POST /weather/v1/sms

{
    "weatherCity": "New York",
    "weatherCountryCode": "US",
    "contact": "1112223333",
    "unit": "F"
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
        "temp": 42,
        "unit": "F",
        "description": "Clear"
    },
    "status": "QUEUED",
    "message": "Hello there, currently it's clear with a temperature of 42 degrees F in New York, US!"
}
```

### POST `weather/v1/voice`
Makes a voice call to an array of phone numbers and delivers the current weather information for a specific location.

**Request**
```json
POST /weather/v1/voice

{
    "weatherCity": "New York",
    "weatherCountryCode": "US",
    "contact": "1112223333",
    "unit": "F"
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
        "temp": 42,
        "unit": "F",
        "description": "Clear"
    },
    "status": "QUEUED",
    "message": "Hello there, currently it's clear with a temperature of 42 degrees F in New York, US!"
}
```

## Test Coverage
<p align="center">
  <img src="./README%20Assets/TestCoverage.png" width="600px"/>
</p>

## Developer Environment Dependencies
- [OpenWeather API](https://openweathermap.org/api) - Weather data API to retrieve weather information.
- [Twilio](https://www.twilio.com/docs/usage/api) - A cloud communications platform for sending SMS and making phone calls.

## Developer Convention Information
- Standard Spring Boot Layout (Controller, Service, Model packages).
- Testing utilizing [Spock framework](https://spockframework.org/).
- Properties hidden in `application.yaml` are stored in a secrets file.

## Future Developments
- Implement GraphQL Integration
