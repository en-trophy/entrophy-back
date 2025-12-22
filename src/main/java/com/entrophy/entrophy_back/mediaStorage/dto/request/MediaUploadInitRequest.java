package com.entrophy.entrophy_back.mediaStorage.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MediaUploadInitRequest(

        @NotBlank
        String fileName,

        @NotBlank
        String contentType
) {
}
