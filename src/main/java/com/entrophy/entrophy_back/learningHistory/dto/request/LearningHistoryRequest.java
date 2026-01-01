package com.entrophy.entrophy_back.learningHistory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LearningHistoryRequest(

        Long userId,

       Long categoryId,

       Long lessonId,

       int score,

       int practiceSeconds,

        String aiFeedback

) { }
