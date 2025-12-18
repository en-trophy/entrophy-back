package com.entrophy.entrophy_back.lesson.dto.request;

public record LessonRequest(
        Long categoryId,
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
