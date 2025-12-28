package com.entrophy.entrophy_back.lesson.entity;


import com.entrophy.entrophy_back.category.entity.Category;
import com.entrophy.entrophy_back.lesson.dto.request.LessonRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    private String title;

    //단어, 문장으로 구분 (word/phrase)
    private String type;

    private String signLanguage;

    private int difficulty;

    private String imageUrl;

    private String videoUrl;


    protected Lesson(){}


    //생성
    public Lesson(Category category, LessonRequest lessonRequest) {
        this.category = category;
        this.title = lessonRequest.title();
        this.signLanguage = lessonRequest.signLanguage();
        this.difficulty =lessonRequest.difficulty();
        this.type =  lessonRequest.type();
        this.imageUrl = lessonRequest.imageUrl();
        this.videoUrl = lessonRequest.videoUrl();
    }

    // 업데이트
    public void update(Category category, LessonRequest lessonRequest) {
        this.category = category;
        this.title = lessonRequest.title();
        this.signLanguage = lessonRequest.signLanguage();
        this.difficulty = lessonRequest.difficulty();
        this.type = lessonRequest.type();
        this.imageUrl = lessonRequest.imageUrl();
        this.videoUrl = lessonRequest.videoUrl();
    }
}