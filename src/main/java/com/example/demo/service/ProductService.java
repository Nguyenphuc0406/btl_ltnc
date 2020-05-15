package com.example.demo.service;

import java.util.List;

import com.example.demo.base.request.GetProductByIdRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductDTO;

public interface ProductService {
	public BaseResponse addProduct(ProductDTO productDTO);

	public List<Product> getAllProduct();

	public Product getProduct(int id);

	public BaseResponse updateProduct(int id, ProductDTO productDTO);

	public BaseResponse deleteProduct(int id);

	public BaseResponse getProductByCategoryId(GetProductByIdRequest request);

	public List<Product> getProductByPage(int pageId, int total);
}
