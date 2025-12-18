package com.entrophy.entrophy_back.lesson.controller;


import com.entrophy.entrophy_back.category.dto.response.CategoryResponse;
import com.entrophy.entrophy_back.lesson.dto.response.LessonResponse;
import com.entrophy.entrophy_back.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;


    // 전체 레슨 조회
    @GetMapping
    public ResponseEntity<List<LessonResponse>> getLessons() {
        List<LessonResponse> lessons = lessonService.getLessons();
        return ResponseEntity.ok(lessons);
    }

    // 레슨 조회
    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getLesson(@PathVariable Long id) {
        LessonResponse lesson = lessonService.getLesson(id);
        return ResponseEntity.ok(lesson);
    }





}
