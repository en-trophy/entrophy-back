package com.entrophy.entrophy_back.learningHistory.dto.response;

import java.time.LocalDateTime;

public record LearningHistoryCreateResponse(

        Long historyId,

        LocalDateTime createdAt
) {
}
