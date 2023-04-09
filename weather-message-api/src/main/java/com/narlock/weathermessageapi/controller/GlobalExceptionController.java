package com.narlock.weathermessageapi.controller;

import com.narlock.weathermessageapi.domain.TwilioClientException;
import com.narlock.weathermessageapi.domain.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler({HttpMessageNotReadableException.class,
            IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e) {
        log.error("Bad Request Occurred", e);
        return createErrorResponse("Request fields are invalid", HttpStatus.BAD_REQUEST);
    }

    /**
     * handleTwilioClientException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(TwilioClientException.class)
    public ResponseEntity<ErrorResponse> handleTwilioClientException(TwilioClientException e) {
        log.error("Twilio Client Error occurred {}", e);
        return createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * catchAll
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchAll(Exception e) {
        log.error("Unexpected error occurred {}", e);
        return createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * createErrorResponse
     *
     * @param status
     * @param message
     * @return
     * @brief Creates an Error Response Object
     */
    private ResponseEntity<ErrorResponse> createErrorResponse(String message, HttpStatus status) {
        ErrorResponse response = ErrorResponse.builder().status(status.value()).errorMessage(message).build();
        return new ResponseEntity<>(response, status);
    }
}
