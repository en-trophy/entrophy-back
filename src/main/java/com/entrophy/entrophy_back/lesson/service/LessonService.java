package com.entrophy.entrophy_back.lesson.service;

import com.entrophy.entrophy_back.category.entity.Category;
import com.entrophy.entrophy_back.category.repository.CategoryRepository;
import com.entrophy.entrophy_back.lesson.dto.request.LessonRequest;
import com.entrophy.entrophy_back.lesson.dto.response.LessonResponse;
import com.entrophy.entrophy_back.lesson.entity.Lesson;
import com.entrophy.entrophy_back.lesson.repository.LessonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CategoryRepository categoryRepository;


    // 생성
    public LessonResponse createLesson(LessonRequest lessonRequest) {
        Category category = categoryRepository.findById(lessonRequest.categoryId()).orElseThrow(() -> new IllegalArgumentException("해당 id의 카테고리 없음"));

        Lesson lesson = new Lesson(category, lessonRequest);
        lessonRepository.save(lesson);
        return toResponseDto(lesson);
    }


    // 전체 레슨 조회
    public List<LessonResponse> getLessons() {
        return lessonRepository.findAll().stream().map(this::toResponseDto).toList();
    }


    //해당 카테고리의 전체 레슨 조회
    public List<LessonResponse> getLessonsByCategory(Long categoryId) {

        //카테고리 존제하는지 검사
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("해당 id의 카테고리 없음");
        }

        return lessonRepository.findByCategory_Id(categoryId).stream().map(this::toResponseDto).toList();
    }


    // 레슨 조회
    public LessonResponse getLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 레슨 없음"));
        return toResponseDto(lesson);
    }


    // 레슨 정보 수정
    @Transactional
    public LessonResponse updateLesson(Long id, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id의 레슨 없음"));

        Category category = lesson.getCategory();

        category = categoryRepository.findById(lessonRequest.categoryId()).orElseThrow(() -> new IllegalArgumentException("해당 id의 카테고리 없음"));

        lesson.update(category, lessonRequest);

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
