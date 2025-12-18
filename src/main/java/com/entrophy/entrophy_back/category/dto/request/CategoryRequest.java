package com.entrophy.entrophy_back.category.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "code는 필수")
        String code,

        @NotBlank(message = "name은 필수")
        String name,

        String iconEmoji,
        String description
) {}
