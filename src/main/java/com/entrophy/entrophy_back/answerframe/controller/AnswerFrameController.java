package com.entrophy.entrophy_back.answerframe.controller;


import com.entrophy.entrophy_back.answerframe.dto.request.AnswerFrameRequest;
import com.entrophy.entrophy_back.answerframe.dto.response.AnswerFrameResponse;
import com.entrophy.entrophy_back.answerframe.service.AnswerFrameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons/{lessonId}/answer-frames")
public class AnswerFrameController {

    private final AnswerFrameService answerFrameService;


    // 정답 프레임 생성
    @PostMapping
    public ResponseEntity<AnswerFrameResponse> createAnswerFrame(@PathVariable Long lessonId, @RequestBody AnswerFrameRequest answerFrameRequest
    ) {
        AnswerFrameResponse answerFrame = answerFrameService.createAnswerFrame(lessonId, answerFrameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(answerFrame);
    }

    // 정답 프레임 조회
    @GetMapping
    public ResponseEntity<List<AnswerFrameResponse>> getAnswerFrames(@PathVariable Long lessonId) {
        List<AnswerFrameResponse> answerFrames = answerFrameService.getAnswerFrames(lessonId);
        return ResponseEntity.ok(answerFrames);
    }
}
