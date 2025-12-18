package com.entrophy.entrophy_back.category.controller;

import com.entrophy.entrophy_back.category.dto.request.CategoryRequest;
import com.entrophy.entrophy_back.category.dto.response.CategoryResponse;
import com.entrophy.entrophy_back.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id){
        CategoryResponse categoryResponse = categoryService.getCategory(id);
        return ResponseEntity.ok(categoryResponse);
    }


    //카테고리 생성
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    //카테고리 삭제



}
