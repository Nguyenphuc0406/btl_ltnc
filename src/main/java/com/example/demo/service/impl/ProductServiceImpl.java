package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.NotFoundResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.data.ProductRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.reponsitory.CategoryRepository;
import com.example.demo.reponsitory.ProductReponsitory;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductReponsitory productReponsitory;
	@Autowired
	CategoryRepository categoryRepo;

	@Override
	public BaseResponse addProduct(ProductDTO productDTO) {

		if (productDTO != null && productDTO.getCategoryId() > 0 && productDTO.getProductName() != null) {
			Category category = categoryRepo.findByCategoryId(productDTO.getCategoryId());
			if (category != null) {

				Product newProduct = new Product();
				newProduct.setCategory(category);
				newProduct.setDescription(productDTO.getDescription());
				newProduct.setProductPrice(productDTO.getPrice());
				newProduct.setProductName(productDTO.getProductName());
				newProduct.setProductImage(productDTO.getImage());

				Product insertP = productReponsitory.save(newProduct);
				return insertP != null ? new OkResponse<String>("Add product successfuly!")
						: new NotFoundResponse("Add product error!");
			} else {
				return new NotFoundResponse("Category not found!");
			}
		} else {
			return new NotFoundResponse("Input invali!");

		}
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> listProduct = new ArrayList<Product>();
		listProduct = productReponsitory.findAll();
		return listProduct;
	}

	@Override
	public Product getProduct(int id) {
		Product product = new Product();
		product = productReponsitory.findByProductId(id);
		return null;
	}

	@Override
	public BaseResponse updateProduct(ProductDTO productDTO) {
		if (productDTO != null && productDTO.getCategoryId() > 0 && productDTO.getProductName() != null) {
			Product productOld = productReponsitory.findByProductId(productDTO.getProductId());
			if (productOld != null) {
				Category category = categoryRepo.findByCategoryId(productDTO.getCategoryId());
				Product newProduct = new Product();
				newProduct.setCategory(category);
				newProduct.setProductId(productDTO.getProductId());
				newProduct.setDescription(productDTO.getDescription());
				newProduct.setProductPrice(productDTO.getPrice());
				newProduct.setProductName(productDTO.getProductName());
				newProduct.setProductImage(productDTO.getImage());
				productReponsitory.updateProductByProductId(newProduct.getProductName(), newProduct.getCategory(),
						newProduct.getProductImage(), newProduct.getProductPrice(), newProduct.getDescription(),
						newProduct.getProductId());
				return new OkResponse<String>("Update product successfuly!");

			} else {
				return new NotFoundResponse("Product not found!");
			}
		} else {
			return new NotFoundResponse("Input invali!");

		}

	}

	@Override
	public BaseResponse deleteProduct(int id) {
		Product product = productReponsitory.findByProductId(id);
		if (product != null) {
			productReponsitory.deleteProductByProductId(id);

			return new OkResponse<String>("Delete product successfuly!");

		} else {
			return new NotFoundResponse("Product not found!");

		}

	}

	@Override
	public List<Product> getProductByPage(int pageId, int total) {
		// TODO Auto-generated method stub
		return null;
	}

}
