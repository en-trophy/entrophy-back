package com.entrophy.entrophy_back.lesson.dto.response;

public record LessonResponse(
        Long id,
        Long categoryId,
        String categoryName,
        String title,
        String signLanguage,
        Integer difficulty,
        String type,
        String imageUrl,
        String videoUrl
) {
}
