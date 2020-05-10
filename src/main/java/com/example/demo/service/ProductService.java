package com.example.demo.service;

import java.util.List;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.data.ProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductDTO;


public interface ProductService {
	public BaseResponse addProduct(ProductDTO productDTO);

	public List<Product> getAllProduct();

	public Product getProduct(int id);

	public BaseResponse updateProduct(ProductDTO productDTO);

	public BaseResponse deleteProduct(int id);
	
	public List<Product> getProductByPage(int pageId, int total);
}
