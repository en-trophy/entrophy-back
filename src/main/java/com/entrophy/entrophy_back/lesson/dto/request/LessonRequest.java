package com.entrophy.entrophy_back.lesson.dto.request;

public record LessonRequest(
        Long categoryId,
        String title,
        String signLanguage,
        Integer difficulty,
        String type,
        String imageUrl,
        String videoUrl

) {
}
