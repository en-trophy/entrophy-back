package com.entrophy.entrophy_back.lesson.dto.response;

import com.entrophy.entrophy_back.lesson.entity.LessonMode;

public record LessonResponse(
        Long id,
        Long categoryId,
        String categoryName,
        String title,
        String signLanguage,
        Integer difficulty,
        String type,
        LessonMode mode,
        String imageUrl,
        String videoUrl
) {
}
