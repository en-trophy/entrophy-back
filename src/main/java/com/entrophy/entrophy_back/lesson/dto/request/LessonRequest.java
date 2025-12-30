package com.entrophy.entrophy_back.lesson.dto.request;

import com.entrophy.entrophy_back.lesson.entity.LessonMode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonRequest(
        @NotNull(message = "categoryId는 필수")
        Long categoryId,

        @NotBlank(message = "title은 필수")
        String title,

        @NotBlank(message = "signLanguage는 필수")
        String signLanguage,

        @NotNull(message = "difficulty는 필수")
        @Min(value = 1, message = "difficulty는 1 이상")
        @Max(value = 5, message = "difficulty는 5 이하")
        Integer difficulty,

        @NotBlank(message = "type은 필수")
        String type,

        @NotNull(message = "mode는 필수")
        LessonMode mode,

        String imageUrl,
        String videoUrl
) {}

