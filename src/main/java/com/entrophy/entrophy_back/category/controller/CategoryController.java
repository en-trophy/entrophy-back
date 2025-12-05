package com.entrophy.entrophy_back.category.controller;

import com.entrophy.entrophy_back.category.dto.response.CategoryResponse;
import com.entrophy.entrophy_back.category.service.CategoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    //전체 카테고리 조회
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories(){
        List<CategoryResponse> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }


    //카테고리 조회
    @GetMapping ResponseEntity<CategoryResponse> getCategory(@PathVariable Integer id){
        CategoryResponse category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }

    //카테고리 생성


    //카테고리 삭제



}
