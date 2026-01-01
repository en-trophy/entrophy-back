package com.entrophy.entrophy_back.learningHistory.repository;

import com.entrophy.entrophy_back.learningHistory.dto.response.LearningHistoryDailySummaryResponse;
import com.entrophy.entrophy_back.learningHistory.entity.LearningHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LearningHistoryRepository extends JpaRepository<LearningHistory, Long> {

    // 날짜 범위 상세 리스트
    List<LearningHistory> findByUser_IdAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long userId, LocalDateTime from, LocalDateTime to
    );

    // 카테고리 + 날짜 범위 상세 리스트
    List<LearningHistory> findByUser_IdAndCategory_IdAndCreatedAtBetweenOrderByCreatedAtDesc(
            Long userId, Long categoryId, LocalDateTime from, LocalDateTime to
    );
}
