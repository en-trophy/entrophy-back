package com.entrophy.entrophy_back.answerframe.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema(description = "정답 프레임 조회/생성 결과 응답 DTO")
public record AnswerFrameResponse(

        Long id,

        Long lessonId,

        Integer seq,

        JsonNode hand,

        JsonNode frameMeta,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
