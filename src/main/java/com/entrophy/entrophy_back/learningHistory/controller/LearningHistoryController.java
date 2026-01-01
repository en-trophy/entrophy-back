package com.entrophy.entrophy_back.learningHistory.controller;

import com.entrophy.entrophy_back.learningHistory.dto.request.LearningHistoryCreateRequest;
import com.entrophy.entrophy_back.learningHistory.dto.response.LearningHistoryCreateResponse;
import com.entrophy.entrophy_back.learningHistory.dto.response.LearningHistoryDailySummaryResponse;
import com.entrophy.entrophy_back.learningHistory.dto.response.LearningHistoryDetailResponse;
import com.entrophy.entrophy_back.learningHistory.service.LearningHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "학습 기록 API", description = "학습 기록 생성, 카테고리별 조회, 날짜별 조회 기능 제공 ")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/learning-histories")
public class LearningHistoryController {

    private final LearningHistoryService learningHistoryService;

    //생성
    @Operation(
            summary = "학습 기록 생성",
            description = "레슨 완료 화면에서 최종 점수/시간/피드백을 저장"
    )
    @PostMapping
    public ResponseEntity<LearningHistoryCreateResponse> create(
            @Valid @RequestBody LearningHistoryCreateRequest request
    ) {
        LearningHistoryCreateResponse response = learningHistoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //카테고리별 조회
    @Operation(
            summary = "카테고리별 학습 기록 조회",
            description = "특정 유저의 특정 카테고리 학습 기록을 날짜 범위로 조회함"
    )

    @GetMapping("/by-category")
    public ResponseEntity<List<LearningHistoryDetailResponse>> getByCategory(
            @Parameter(description = "유저 PK", example = "1") @RequestParam Long userId,
            @Parameter(description = "카테고리 PK", example = "4") @RequestParam Long categoryId,
            @Parameter(description = "시작 날짜(포함)", example = "2025-12-01") @RequestParam LocalDate from,
            @Parameter(description = "종료 날짜(포함)", example = "2025-12-31") @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok(learningHistoryService.getByCategory(userId, categoryId, from, to));
    }



    //특정 날짜 학습 기록
    @Operation(
            summary = "특정 날짜 학습 기록 상세 조회",
            description = "날짜를 클릭했을 때 해당 날짜의 학습 로그(레슨/점수/피드백)를 반환함"
    )
    @GetMapping
    public ResponseEntity<List<LearningHistoryDetailResponse>> getByDate(
            @Parameter(description = "유저 PK", example = "1") @RequestParam Long userId,
            @Parameter(description = "조회 날짜", example = "2025-12-31") @RequestParam LocalDate date
    ) {
        return ResponseEntity.ok(learningHistoryService.getByDate(userId, date));
    }
}
