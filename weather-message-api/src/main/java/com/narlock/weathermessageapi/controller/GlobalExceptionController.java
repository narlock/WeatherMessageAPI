package com.narlock.weathermessageapi.controller;

import com.narlock.weathermessageapi.domain.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchAll(Exception e) {
        return new ResponseEntity<>(
                createErrorResponse(500, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * createErrorResponse
     * @brief Creates an Error Response Object
     * @param status
     * @param message
     * @return
     */
    private ErrorResponse createErrorResponse(int status, String message) {
        return ErrorResponse.builder().status(status).errorMessage(message).build();
    }
}
