package com.entrophy.entrophy_back.mediaStorage.dto.response;

public record MediaUploadInitResponse(
        String
        blobName,

        String
        uploadUrl
) {
}
