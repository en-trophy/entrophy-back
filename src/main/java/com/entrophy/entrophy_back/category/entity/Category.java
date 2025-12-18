package com.entrophy.entrophy_back.category.entity;

import com.entrophy.entrophy_back.category.dto.request.CategoryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String iconEmoji;

    private String description;


    protected Category() {}

    //생성
    public Category(CategoryRequest categoryRequest) {
        this.code = categoryRequest.code();
        this.name = categoryRequest.name();
        this.iconEmoji = categoryRequest.iconEmoji();
        this.description = categoryRequest.description();
    }
}
