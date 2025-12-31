package com.entrophy.entrophy_back.learningHistory.entity;


import com.entrophy.entrophy_back.category.entity.Category;
import com.entrophy.entrophy_back.lesson.entity.Lesson;
import com.entrophy.entrophy_back.user.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "learning_history")
@Getter
public class LearningHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    private int score;

    private int practiceSeconds;

    private String aiFeedback;

    private LocalDateTime createdAt;


    protected LearningHistory (){}










}
