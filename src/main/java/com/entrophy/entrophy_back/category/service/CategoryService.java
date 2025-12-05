package com.entrophy.entrophy_back.category.service;


import com.entrophy.entrophy_back.category.dto.request.CategoryRequest;
import com.entrophy.entrophy_back.category.dto.response.CategoryResponse;
import com.entrophy.entrophy_back.category.entity.Category;
import com.entrophy.entrophy_back.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //카테고리 생성
    public CategoryResponse createCategory(CategoryRequest categoryRequest){

        Category category = new Category(categoryRequest);

        Category savedCategory = categoryRepository.save(category);

        return toResponseDto(savedCategory);
    }

    //전체 카테고리 조회
    public List<CategoryResponse>getCategories(){
        return categoryRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    //카테고리 조회
    public CategoryResponse getCategory(Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        return toResponseDto(category);
    }


    private CategoryResponse toResponseDto(Category category){
        return (new CategoryResponse(
                category.getId(),
                category.getCode(),
                category.getName(),
                category.getIconEmoji(),
                category.getDescription()));
    }
}
