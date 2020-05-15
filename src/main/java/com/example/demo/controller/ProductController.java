package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.request.GetProductByIdRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.service.ProductService;
import com.example.demo.utils.ConfigUrl;

@RestController
@RequestMapping(ConfigUrl.URL_PRODUCT)
public class ProductController {
	@Autowired
	ProductService productservice;

	@GetMapping()
	public List<Product> getAllProduct() {
		return productservice.getAllProduct();
	}

	@PostMapping()
	public BaseResponse addProduct(@RequestBody ProductDTO productDTO) {
		return productservice.addProduct(productDTO);
	}

	@PutMapping(path = "/{id}")
	public BaseResponse updateProduct(@PathVariable("id") int productId, @RequestBody ProductDTO productDTO) {
		return productservice.updateProduct(productId, productDTO);
	}

	@DeleteMapping(path = "/{id}")
	public BaseResponse deleteProduct(@PathVariable("id") int productId) {
		return productservice.deleteProduct(productId);
	}

	@PostMapping(ConfigUrl.GET_PRODUCT_BY_ID)
	public BaseResponse getProductById(@RequestBody GetProductByIdRequest request) {
		return productservice.getProductByCategoryId(request);
	}

}
