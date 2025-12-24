package com.entrophy.entrophy_back.mediaStorage.controller;

import com.entrophy.entrophy_back.mediaStorage.dto.response.MediaUploadResponse;
import com.entrophy.entrophy_back.mediaStorage.model.MediaType;
import com.entrophy.entrophy_back.mediaStorage.service.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
public class MediaController {

    private final MediaService mediaService;

    @Value("${admin.upload-key}")
    private String adminUploadKey;

    private void checkAdminKey(String adminKey) {
        if (adminUploadKey == null || adminUploadKey.isBlank()) {
            throw new IllegalStateException("ADMIN_UPLOAD_KEY 입력");
        }
        if (!adminUploadKey.equals(adminKey)) {
            throw new IllegalArgumentException("admin key 불일치");
        }
    }

    @Operation(summary = "이미지 업로드", description = "[관리자용] 이미지 파일을 업로드하고 Blob URL 반환 (X-ADMIN-KEY 필요)")
    @PostMapping(value="/images", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaUploadResponse> uploadImage(
            @Parameter(description = "관리자 업로드 키", required = true)
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @RequestPart("file") MultipartFile file
    ) throws Exception {
        checkAdminKey(adminKey);
        return ResponseEntity.ok(mediaService.upload(MediaType.IMAGE, file));
    }

    @Operation(summary = "동영상 업로드", description = "[관리자용] 동영상 파일을 업로드하고 Blob URL 반환 (X-ADMIN-KEY 필요)")
    @PostMapping(value="/videos", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaUploadResponse> uploadVideo(
            @Parameter(description = "관리자 업로드 키", required = true)
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @RequestPart("file") MultipartFile file
    ) throws Exception {
        checkAdminKey(adminKey);
        return ResponseEntity.ok(mediaService.upload(MediaType.VIDEO, file));
    }
}