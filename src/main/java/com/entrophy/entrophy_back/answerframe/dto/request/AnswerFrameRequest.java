package com.entrophy.entrophy_back.answerframe.dto.request;

import tools.jackson.databind.JsonNode;

public record AnswerFrameRequest(
        Integer seq,

        JsonNode hand,

        JsonNode frameMeta
) {
}
