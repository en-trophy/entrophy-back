package com.entrophy.entrophy_back.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(


        @NotBlank
        String loginId,

        @NotBlank
        String password


) {
}
