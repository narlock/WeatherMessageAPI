package com.narlock.weathermessageapi.client;


import com.narlock.weathermessageapi.domain.TwilioClientException;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwilioClient {

    @Value("${weather.client.twilio.accountSid}")
    private String twilioAccountSid;

    @Value("${weather.client.twilio.authToken}")
    private String twilioAuthToken;

    @Value("${weather.client.twilio.fromNumber}")
    private String twilioFromNumber;

    public TwilioClient() {
        Twilio.init(twilioAccountSid, twilioAuthToken);
    }

    public void sendMessage(String toNumber, String messageBody) {
        try {
            Message message = Message.creator(new PhoneNumber(toNumber), new PhoneNumber(twilioFromNumber), messageBody).create();
            System.out.println("Message SID: " + message.getSid());
        } catch (ApiException e) {
            throw new TwilioClientException("Failed to send SMS: " + e.getMessage(), e);
        }}

    public void makeVoiceCall(String toNumber, String fromNumber, String message) {
        try {
            Call call = Call.creator(
                    new PhoneNumber(toNumber),
                    new PhoneNumber(fromNumber),
                    new com.twilio.type.Twiml("<Response><Say>" + message + "</Say></Response>")
            ).create();

            System.out.println(call.getSid());
        } catch (Exception e) {
            throw new TwilioClientException("Error making voice call to " + toNumber, e);
        }
    }
}