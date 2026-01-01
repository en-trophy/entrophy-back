package com.entrophy.entrophy_back.learningHistory.dto.request;

public record LearningHistoryCreateRequest(

        Long userId,

       Long categoryId,

       Long lessonId,

       int score,

       int practiceSeconds,

        String aiFeedback

) { }
