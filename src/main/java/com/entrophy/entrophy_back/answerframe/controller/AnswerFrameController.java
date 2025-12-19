package com.entrophy.entrophy_back.answerframe.controller;


import com.entrophy.entrophy_back.answerframe.dto.request.AnswerFrameRequest;
import com.entrophy.entrophy_back.answerframe.dto.response.AnswerFrameResponse;
import com.entrophy.entrophy_back.answerframe.service.AnswerFrameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons/{lessonId}/answer-frames")
@Tag(name = "AI용) 정답 프레임 API", description = "정답프레임 생성, 조회, 수정 기능 제공")
public class AnswerFrameController {

    private final AnswerFrameService answerFrameService;


    // 정답 프레임 생성
    @Operation(summary = "정답 프레임 생성", description = "특정 레슨에 대한 정답 프레임 데이터를 생성")
    @PostMapping
    public ResponseEntity<AnswerFrameResponse> createAnswerFrame(@PathVariable Long lessonId, @Valid @RequestBody AnswerFrameRequest answerFrameRequest
    ) {
        AnswerFrameResponse answerFrame = answerFrameService.createAnswerFrame(lessonId, answerFrameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(answerFrame);
    }

    // 정답 프레임 조회
    @Operation(summary = "정답 프레임 조회", description = "특정 레슨에 대한 정답 프레임 데이터를 조회")
    @GetMapping
    public ResponseEntity<List<AnswerFrameResponse>> getAnswerFrames(@PathVariable Long lessonId) {
        List<AnswerFrameResponse> answerFrames = answerFrameService.getAnswerFrames(lessonId);
        return ResponseEntity.ok(answerFrames);
    }

    // 정답 프레임 수정
    @PutMapping("/{seq}")
    @Operation(summary = "정답 프레임 수정", description = "정답 프레임 seq째 데이터 정보 수정")
    public ResponseEntity<AnswerFrameResponse> updateAnswerFrame(@PathVariable Long lessonId, @PathVariable Integer seq, @Valid @RequestBody AnswerFrameRequest answerFrameRequest) {
        AnswerFrameResponse answerFrameResponse = answerFrameService.updateAnswerFrame(lessonId, seq, answerFrameRequest);
        return ResponseEntity.ok(answerFrameResponse);
    }
}
