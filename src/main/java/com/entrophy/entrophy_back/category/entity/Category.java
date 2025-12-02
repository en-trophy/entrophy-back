package com.entrophy.entrophy_back.category.entity;

import com.entrophy.entrophy_back.category.dto.request.CategoryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String name;

    private String iconEmoji;

    private String description;


    protected Category() {}

    public void setCategory(CategoryRequest categoryRequest) {
        this.code = categoryRequest.code();
        this.name = categoryRequest.name();
        this.iconEmoji = categoryRequest.iconEmoji();
        this.description = categoryRequest.description();
    }
}
