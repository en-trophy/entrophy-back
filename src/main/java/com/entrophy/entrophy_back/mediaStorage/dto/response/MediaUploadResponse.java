package com.entrophy.entrophy_back.mediaStorage.dto.response;

public record MediaUploadResponse(
        String
        blobName,

        String
        uploadUrl
) {
}
