package com.entrophy.entrophy_back.mediaStorage.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MediaUploadRequest(

        @NotBlank
        String fileName,

        @NotBlank
        String contentType
) {
}
