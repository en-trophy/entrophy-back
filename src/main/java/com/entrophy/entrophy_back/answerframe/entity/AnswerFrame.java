package com.entrophy.entrophy_back.answerframe.entity;

import com.entrophy.entrophy_back.answerframe.dto.request.AnswerFrameRequest;
import com.entrophy.entrophy_back.lesson.entity.Lesson;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "answer_frame")
public class AnswerFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(nullable = false)
    private Integer seq = 1;

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode hand;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private JsonNode frameMeta;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    protected AnswerFrame(){}

    //생성
    public AnswerFrame(Lesson lesson, AnswerFrameRequest answerFrameRequest) {
        this.lesson = lesson;
        this.seq = answerFrameRequest.seq();
        this.hand = answerFrameRequest.hand();
        this.frameMeta = answerFrameRequest.frameMeta();
    }
}
