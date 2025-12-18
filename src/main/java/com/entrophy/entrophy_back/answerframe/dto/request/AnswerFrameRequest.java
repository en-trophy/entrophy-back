package com.entrophy.entrophy_back.answerframe.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "레슨의 정답 프레임 생성/수정 요청 DTO")
public record AnswerFrameRequest(

        @Min(value = 1, message = "seq는 1 이상")
        Integer seq,

        @NotNull(message = "hand는 필수 입력값")
        JsonNode hand,

        JsonNode frameMeta
) {
}
