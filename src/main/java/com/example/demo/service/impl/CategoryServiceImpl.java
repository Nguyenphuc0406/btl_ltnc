package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.base.request.GetProductByIdRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.NotFoundResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.model.CategoryDTO;
import com.example.demo.reponsitory.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public BaseResponse addCategory(CategoryDTO categoryDTO) {
		if (categoryDTO != null && categoryDTO.getCategoryName() != null) {
			// Category category =
			// categoryRepository.findByCategoryId(productDTO.getCategoryId());

//			if (category != null) {
//
//				Product newProduct = new Product();
//				newProduct.setCategory(category);
//				newProduct.setDescription(productDTO.getDescription());
//				newProduct.setProductPrice(productDTO.getPrice());
//				newProduct.setProductName(productDTO.getProductName());
//				newProduct.setProductImage(productDTO.getImage());
			Category category = new Category();
			category.setCategoryName(categoryDTO.getCategoryName());
			category.setDescription(categoryDTO.getDescription());
			category.setCategoryImage(categoryDTO.getCategoryImage());
			Category insertC = categoryRepository.save(category);
			return insertC != null ? new OkResponse<String>("Add category successfuly!")
					: new NotFoundResponse("Add category error!");
		} else {

			return new NotFoundResponse("Input invali!");

		}
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(int id) {
		return categoryRepository.findByCategoryId(id);
	}

	@Override
	public BaseResponse deleteCategory(int id) {
		Category category = categoryRepository.findByCategoryId(id);
		if (category != null) {
			categoryRepository.deleteById(id);

			return new OkResponse<String>("Delete product successfuly!");

		} else {
			return new NotFoundResponse("Product not found!");

		}
	}

	@Override
	public BaseResponse getCategoryById(GetProductByIdRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse updateCategory(int id, CategoryDTO categoryDTO) {
		if (categoryDTO != null && categoryDTO.getCategoryName() != null) {

			Category categoryOld = categoryRepository.findByCategoryId(id);
			if (categoryOld != null) {
				Category category = new Category();
				category.setCategoryImage(categoryDTO.getCategoryImage());
				category.setCategoryName(categoryDTO.getCategoryName());
				category.setDescription(categoryDTO.getDescription());

				categoryRepository.updateCategoryByCategoryId(categoryDTO.getCategoryName(),
						categoryDTO.getCategoryImage(), categoryDTO.getDescription(), id);
				return new OkResponse<String>("Update category successfuly!");
			} else {
				return new NotFoundResponse("Input invali!");
			}
		} else {
			return new NotFoundResponse("Category not found!");
		}
	}

//		return categoryRepository.updateProductByProductId(categoryName, categoryImage, description, categoryId);

}
