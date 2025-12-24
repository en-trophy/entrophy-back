package com.entrophy.entrophy_back.mediaStorage.service;

import com.entrophy.entrophy_back.mediaStorage.dto.response.MediaUploadResponse;
import com.entrophy.entrophy_back.mediaStorage.model.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final AzureBlobStorageService azureBlobStorageService;

    @Value("${azure.storage.image-container}")
    private String imageContainer;

    @Value("${azure.storage.video-container}")
    private String videoContainer;

    @Value("${media.upload.image-max-mb:10}")
    private long imageMaxMb;

    @Value("${media.upload.video-max-mb:200}")
    private long videoMaxMb;

    public MediaUploadResponse upload(MediaType type, MultipartFile file) throws IOException {
        validateFile(type, file);

        String container = containerOf(type);
        String blobName = buildBlobName(type, file.getOriginalFilename());

        String url = azureBlobStorageService.upload(
                container,
                blobName,
                file.getInputStream(),
                file.getSize(),
                file.getContentType()
        );

        return new MediaUploadResponse(type, blobName, url);
    }


    private void validateFile(MediaType type, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file is required");
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException("contentType is required");
        }

        // 타입별 contentType 검증
        if (type == MediaType.IMAGE && !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image/* contentType is allowed");
        }
        if (type == MediaType.VIDEO && !contentType.startsWith("video/")) {
            throw new IllegalArgumentException("Only video/* contentType is allowed");
        }

        // 타입별 용량 제한
        long maxBytes = (type == MediaType.IMAGE ? imageMaxMb : videoMaxMb) * 1024 * 1024;
        if (file.getSize() > maxBytes) {
            throw new IllegalArgumentException("File is too large. max=" +
                    (type == MediaType.IMAGE ? imageMaxMb : videoMaxMb) + "MB");
        }
    }

    private String containerOf(MediaType type) {
        return (type == MediaType.IMAGE) ? imageContainer : videoContainer;
    }

    private String buildBlobName(MediaType type, String originalFilename) {
        String base = (originalFilename == null || originalFilename.isBlank()) ? "file" : originalFilename;

        String safe = base.replaceAll("\\s+", "_")
                .replaceAll("[^a-zA-Z0-9._-]", "_");

        String folder = (type == MediaType.IMAGE) ? "images" : "videos";

        return folder + "/" + UUID.randomUUID() + "_" + safe;
    }
}
