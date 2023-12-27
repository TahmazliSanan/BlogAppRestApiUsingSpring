package com.project.blogapprestapi.repositories;

import com.project.blogapprestapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}