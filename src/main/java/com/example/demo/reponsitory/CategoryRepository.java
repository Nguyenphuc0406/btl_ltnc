package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByCategoryId(int categoryId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Category c SET c.categoryName = ?1, c.categoryImage = ?2, c.description=?3 WHERE c.categoryId = ?4")
	void updateCategoryByCategoryId(String categoryName, String categoryImage, String description, int categoryId);
}
