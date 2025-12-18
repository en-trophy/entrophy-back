package com.entrophy.entrophy_back.answerframe.service;

import com.entrophy.entrophy_back.answerframe.dto.request.AnswerFrameRequest;
import com.entrophy.entrophy_back.answerframe.dto.response.AnswerFrameResponse;
import com.entrophy.entrophy_back.answerframe.entity.AnswerFrame;
import com.entrophy.entrophy_back.answerframe.repository.AnswerFrameRepository;
import com.entrophy.entrophy_back.lesson.entity.Lesson;
import com.entrophy.entrophy_back.lesson.repository.LessonRepository;
import jakarta.transaction.Transactional;
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

    // 정답 프레임 수정
    @Transactional
    public AnswerFrameResponse updateAnswerFrame(Long lessonId, Integer seq, AnswerFrameRequest answerFrameRequest) {

        AnswerFrame frame = answerFrameRepository.findByLessonIdAndSeq(lessonId, seq).orElseThrow(() -> new IllegalArgumentException("해당 레슨/seq의 정답 프레임 없음"));

        frame.update(answerFrameRequest);

        return toResponseDto(frame);
    }

    private AnswerFrameResponse toResponseDto(AnswerFrame answerFrame) {
        return new AnswerFrameResponse(
                answerFrame.getId(),
                answerFrame.getLesson().getId(),
                answerFrame.getSeq(),
                answerFrame.getHand(),
                answerFrame.getFrameMeta(),
                answerFrame.getCreatedAt(),
                answerFrame.getUpdatedAt()
        );
    }

}
