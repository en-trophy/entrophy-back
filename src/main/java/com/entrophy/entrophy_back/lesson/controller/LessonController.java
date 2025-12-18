package com.entrophy.entrophy_back.lesson.controller;


import com.entrophy.entrophy_back.category.dto.response.CategoryResponse;
import com.entrophy.entrophy_back.lesson.dto.request.LessonRequest;
import com.entrophy.entrophy_back.lesson.dto.response.LessonResponse;
import com.entrophy.entrophy_back.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;


    // 레슨 생성
    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@RequestBody LessonRequest lessonRequest) {
        LessonResponse created = lessonService.createLesson(lessonRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 전체 레슨 조회
    @GetMapping
    public ResponseEntity<List<LessonResponse>> getLessons() {
        List<LessonResponse> lessons = lessonService.getLessons();
        return ResponseEntity.ok(lessons);
    }

    //해당 카테고리의 전체 레슨 조회
    @GetMapping(params = "categoryId")
    public ResponseEntity<List<LessonResponse>> getLessonsByCategory(@RequestParam Long categoryId) {
        List<LessonResponse> lessons = lessonService.getLessonsByCategory(categoryId);
        return ResponseEntity.ok(lessons);
    }

    // 레슨 조회
    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getLesson(@PathVariable Long id) {
        LessonResponse lesson = lessonService.getLesson(id);
        return ResponseEntity.ok(lesson);
    }

    // 레슨 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        LessonResponse lesson = lessonService.updateLesson(id, lessonRequest);
        return ResponseEntity.ok(lesson);
    }


}
