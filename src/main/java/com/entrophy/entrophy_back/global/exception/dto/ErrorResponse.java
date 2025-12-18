package com.entrophy.entrophy_back.global.exception.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        int status,
        HttpStatus httpStatus,
        String message
) {
}
