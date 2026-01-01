package com.entrophy.entrophy_back.learningHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "학습 기록 상세 응답 DTO")
public record LearningHistoryDetailResponse(

        @Schema(description = "학습 기록 PK", example = "100")
        Long historyId,

        @Schema(description = "레슨 PK", example = "12")
        Long lessonId,

        @Schema(description = "레슨 제목", example = "I LOVE YOU")
        String lessonTitle,

        @Schema(description = "카테고리 PK", example = "4")
        Long categoryId,

        @Schema(description = "카테고리 이름", example = "Emotions")
        String categoryName,

        @Schema(description = "점수", example = "90")
        int score,

        @Schema(description = "연습 시간(초)", example = "122")
        int practiceSeconds,

        @Schema(description = "AI 피드백", example = "이것저것 적혀있겠죠?")
        String aiFeedback,

        @Schema(description = "학습 완료 시각", example = "2025-12-31T00:50:11")
        LocalDateTime createdAt
) {}
