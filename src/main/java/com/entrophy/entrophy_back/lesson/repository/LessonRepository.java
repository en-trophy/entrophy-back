package com.entrophy.entrophy_back.lesson.repository;

import com.entrophy.entrophy_back.lesson.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCategory_Id(Long categoryId);
}
