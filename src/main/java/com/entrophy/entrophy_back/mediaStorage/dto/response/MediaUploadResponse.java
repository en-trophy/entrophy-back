package com.entrophy.entrophy_back.mediaStorage.dto.response;

import com.entrophy.entrophy_back.mediaStorage.model.MediaType;

public record MediaUploadResponse(
        MediaType
        type,

        String
        blobName,

        String
        uploadUrl
) {
}
