package com.entrophy.entrophy_back.answerframe.repository;

import com.entrophy.entrophy_back.answerframe.entity.AnswerFrame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerFrameRepository extends JpaRepository<AnswerFrame, Long> {

    List<AnswerFrame> findByLessonIdOrderBySeqAsc(Long lessonId);
    Optional<AnswerFrame> findByLessonIdAndSeq(Long lessonId, Integer seq);
}