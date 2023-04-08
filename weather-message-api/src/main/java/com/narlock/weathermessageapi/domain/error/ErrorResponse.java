package com.narlock.weathermessageapi.domain.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String errorMessage;
    private int status;
}
