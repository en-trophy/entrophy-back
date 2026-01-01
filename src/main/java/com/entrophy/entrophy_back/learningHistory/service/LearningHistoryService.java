package com.entrophy.entrophy_back.learningHistory.service;

import com.entrophy.entrophy_back.category.entity.Category;
import com.entrophy.entrophy_back.category.repository.CategoryRepository;
import com.entrophy.entrophy_back.learningHistory.dto.request.LearningHistoryCreateRequest;
import com.entrophy.entrophy_back.learningHistory.dto.response.LearningHistoryCreateResponse;
import com.entrophy.entrophy_back.learningHistory.dto.response.LearningHistoryDailySummaryResponse;
import com.entrophy.entrophy_back.learningHistory.dto.response.LearningHistoryDetailResponse;
import com.entrophy.entrophy_back.learningHistory.entity.LearningHistory;
import com.entrophy.entrophy_back.learningHistory.repository.LearningHistoryRepository;
import com.entrophy.entrophy_back.lesson.entity.Lesson;
import com.entrophy.entrophy_back.lesson.repository.LessonRepository;
import com.entrophy.entrophy_back.user.entity.Users;
import com.entrophy.entrophy_back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LearningHistoryService {

    private final LearningHistoryRepository learningHistoryRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LessonRepository lessonRepository;

    // 학습 기록 생성
    public LearningHistoryCreateResponse create(LearningHistoryCreateRequest req) {
        Users user = userRepository.findById(req.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + req.userId()));

        Category category = categoryRepository.findById(req.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + req.categoryId()));

        Lesson lesson = lessonRepository.findById(req.lessonId())
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found: " + req.lessonId()));

        LearningHistory saved = learningHistoryRepository.save(new LearningHistory(user, category, lesson, req));
        return new LearningHistoryCreateResponse(saved.getId(), saved.getCreatedAt());
    }

    // 카테고리별 학습 기록 조회 (날짜 범위)
    @Transactional(readOnly = true)
    public List<LearningHistoryDetailResponse> getByCategory(Long userId, Long categoryId, LocalDate from, LocalDate to) {
        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.plusDays(1).atStartOfDay().minusNanos(1);

        return learningHistoryRepository
                .findByUser_IdAndCategory_IdAndCreatedAtBetweenOrderByCreatedAtDesc(userId, categoryId, start, end)
                .stream()
                .map(this::toDetail)
                .toList();
    }

    // 날짜별 학습 요약 조회 (캘린더용)
    @Transactional(readOnly = true)
    public List<LearningHistoryDailySummaryResponse> getDailySummary(Long userId, LocalDate from, LocalDate to) {
        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.plusDays(1).atStartOfDay().minusNanos(1);
        return learningHistoryRepository.getDailySummary(userId, start, end);
    }

    // 특정 날짜 상세 로그 조회
    @Transactional(readOnly = true)
    public List<LearningHistoryDetailResponse> getByDate(Long userId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay().minusNanos(1);

        return learningHistoryRepository
                .findByUser_IdAndCreatedAtBetweenOrderByCreatedAtDesc(userId, start, end)
                .stream()
                .map(this::toDetail)
                .toList();
    }

    private LearningHistoryDetailResponse toDetail(LearningHistory h) {
        return new LearningHistoryDetailResponse(
                h.getId(),
                h.getLesson().getId(),
                h.getLesson().getTitle(),
                h.getCategory().getId(),
                h.getCategory().getName(),
                h.getScore(),
                h.getPracticeSeconds(),
                h.getAiFeedback(),
                h.getCreatedAt()
        );
    }
}
