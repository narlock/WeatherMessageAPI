package com.narlock.weathermessageapi.controller;

import com.narlock.weathermessageapi.domain.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionController {

    /**
     * createErrorResponse
     * @brief Creates an Error Response Object
     * @param status
     * @param message
     * @return
     */
    private ErrorResponse createErrorResponse(int status, String message) {
        return ErrorResponse.builder().status(status).message(message).build();
    }
}
