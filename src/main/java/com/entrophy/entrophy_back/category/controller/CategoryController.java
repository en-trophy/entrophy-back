package com.entrophy.entrophy_back.category.controller;

import com.entrophy.entrophy_back.category.dto.request.CategoryRequest;
import com.entrophy.entrophy_back.category.dto.response.CategoryResponse;
import com.entrophy.entrophy_back.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "카테고리 API", description = "카테고리 생성, 전체 조회, 특정 카테고리 조회 기능 제공")
public class CategoryController {

    private final CategoryService categoryService;


    //전체 카테고리 조회
    @Operation(summary = "전체 카테고리 조회", description = "인사 , 감정, 반응, 의문문 등 ")
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories(){
        List<CategoryResponse> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }


    //카테고리 조회
    @GetMapping("/{id}")
    @Operation(summary = "ID로 특정 카테고리 조회", description = "1부터 시작하는 ID값")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id){
        CategoryResponse categoryResponse = categoryService.getCategory(id);
        return ResponseEntity.ok(categoryResponse);
    }


    //카테고리 생성
    @PostMapping
    @Operation(summary = "[관리자용] 카테고리 생성", description = "카테고리 생성")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    //카테고리 삭제



}
