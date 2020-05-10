package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByCategoryId(int categoryId);
}
