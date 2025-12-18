package com.entrophy.entrophy_back.lesson.dto.response;

public record LessonResponse(
        Long id,
        Long categoryId,
        String categoryName,
        String title,
        String lessonKey,
        String signLanguage,
        Integer difficulty,
        String type,
        Boolean isActive,
        String imageUrl,
        String videoUrl
) {
}
