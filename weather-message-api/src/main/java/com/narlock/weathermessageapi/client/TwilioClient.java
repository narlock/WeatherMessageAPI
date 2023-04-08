package com.narlock.weathermessageapi.client;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TwilioClient {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.fromNumber}")
    private String fromNumber;

    @PostConstruct
    public void setup() {
        Twilio.init(accountSid, authToken);
        log.info("Registering TwilioClient with accountSid={}, authToken={}, fromNumber={}", accountSid, authToken, fromNumber);
    }

    public String sendSms(String phoneNumber, String message) {
        Message twilioMessage = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(fromNumber),
                message
        ).create();

        return twilioMessage.getStatus().toString();
    }
}
