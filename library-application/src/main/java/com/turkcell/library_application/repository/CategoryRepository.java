package com.turkcell.library_application.repository;

import com.turkcell.library_application.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
