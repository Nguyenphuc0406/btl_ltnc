package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.entity.Category;
import com.example.demo.model.CategoryDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.utils.ConfigUrl;

@RestController
@RequestMapping(ConfigUrl.URL_CATEGORY)
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@PostMapping
	public BaseResponse addCategory(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.addCategory(categoryDTO);
	}

	@GetMapping
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}

}
