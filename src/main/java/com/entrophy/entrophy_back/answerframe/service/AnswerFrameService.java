package com.entrophy.entrophy_back.answerframe.service;

import com.entrophy.entrophy_back.answerframe.dto.request.AnswerFrameRequest;
import com.entrophy.entrophy_back.answerframe.dto.response.AnswerFrameResponse;
import com.entrophy.entrophy_back.answerframe.entity.AnswerFrame;
import com.entrophy.entrophy_back.answerframe.repository.AnswerFrameRepository;
import com.entrophy.entrophy_back.lesson.entity.Lesson;
import com.entrophy.entrophy_back.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AnswerFrameService {

    private final AnswerFrameRepository answerFrameRepository;
    private final LessonRepository lessonRepository;

    // 정답 프레임 생성
    public AnswerFrameResponse createAnswerFrame(Long lessonId, AnswerFrameRequest answerFrameRequest) {

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new IllegalArgumentException("해당 id의 레슨 없음"));

        AnswerFrame answerFrame = new AnswerFrame(lesson, answerFrameRequest);

        AnswerFrame saved = answerFrameRepository.save(answerFrame);

        return toResponseDto(saved);
    }

    // 레슨 정답 프레임 조회
    public List<AnswerFrameResponse> getAnswerFrames(Long lessonId) {
        return answerFrameRepository.findByLessonIdOrderBySeqAsc(lessonId).stream().map(this::toResponseDto).toList();
    }

    private AnswerFrameResponse toResponseDto(AnswerFrame frame) {
        return new AnswerFrameResponse(
                frame.getId(),
                frame.getLesson().getId(),
                frame.getSeq(),
                frame.getHand(),
                frame.getFrameMeta(),
                frame.getCreatedAt(),
                frame.getUpdatedAt()
        );
    }


}
