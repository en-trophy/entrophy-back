package com.entrophy.entrophy_back.answerframe.dto.request;

import com.fasterxml.jackson.databind.JsonNode;

public record AnswerFrameRequest(
        Integer seq,

        JsonNode hand,

        JsonNode frameMeta
) {
}
