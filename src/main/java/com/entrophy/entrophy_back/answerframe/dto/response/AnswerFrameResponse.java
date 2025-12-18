package com.entrophy.entrophy_back.answerframe.dto.response;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;

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
