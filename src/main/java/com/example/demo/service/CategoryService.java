package com.example.demo.service;

import java.util.List;

import com.example.demo.base.request.GetProductByIdRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.entity.Category;
import com.example.demo.model.CategoryDTO;

public interface CategoryService {
	public BaseResponse addCategory(CategoryDTO categoryDTO);

	public List<Category> getAllCategory();

	public Category getCategory(int id);

	public BaseResponse updateCategory(CategoryDTO categoryDTO);

	public BaseResponse deleteCategory(int id);

	public BaseResponse getCategoryById(GetProductByIdRequest request);

	//public List<Product> getProductByPage(int pageId, int total);
}
