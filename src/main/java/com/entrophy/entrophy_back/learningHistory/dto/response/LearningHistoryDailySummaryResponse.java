package com.entrophy.entrophy_back.learningHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "날짜별 학습 요약 DTO")
public record LearningHistoryDailySummaryResponse(

        @Schema(description = "날짜", example = "2025-12-31")
        LocalDate date,

        @Schema(description = "학습 횟수", example = "3")
        long count,

        @Schema(description = "총 점수", example = "240")
        long totalScore,

        @Schema(description = "총 연습 시간(초)", example = "620")
        long totalPracticeSeconds
) {}
