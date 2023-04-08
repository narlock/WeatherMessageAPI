package com.narlock.weathermessageapi.domain;

public class TwilioClientException extends RuntimeException {
    public TwilioClientException(String message) {
        super(message);
    }
}
