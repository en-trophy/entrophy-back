package com.entrophy.entrophy_back.lesson.service;

import com.entrophy.entrophy_back.category.repository.CategoryRepository;
import com.entrophy.entrophy_back.lesson.dto.response.LessonResponse;
import com.entrophy.entrophy_back.lesson.entity.Lesson;
import com.entrophy.entrophy_back.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CategoryRepository categoryRepository;

    // 전체 레슨 조회
    public List<LessonResponse> getLessons() {
        return lessonRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    // 레슨 조회
    public LessonResponse getLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 레슨 없음"));
        return toResponseDto(lesson);
    }


    private LessonResponse toResponseDto(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getCategory().getId(),
                lesson.getCategory().getName(),
                lesson.getTitle(),
                lesson.getSignLanguage(),
                lesson.getDifficulty(),
                lesson.getType(),
                lesson.getImageUrl(),
                lesson.getVideoUrl()
        );
    }

}
