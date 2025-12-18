package com.entrophy.entrophy_back.category.repository;

import com.entrophy.entrophy_back.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Long> {

}