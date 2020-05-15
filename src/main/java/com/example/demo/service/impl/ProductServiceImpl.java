package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.base.request.GetProductByIdRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.NotFoundResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.config.Config;
import com.example.demo.config.LocalMessageUtils;
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
	public BaseResponse updateProduct(int id, ProductDTO productDTO) {
		Product productOld = productReponsitory.findByProductId(id);
		if (productOld != null) {
			if (productDTO != null && productDTO.getCategoryId() > 0 && productDTO.getProductName() != null) {
				Category category = categoryRepo.findByCategoryId(productDTO.getCategoryId());
				Product newProduct = new Product();
				newProduct.setCategory(category);
				// newProduct.setProductId(id);
				newProduct.setDescription(productDTO.getDescription());
				newProduct.setProductPrice(productDTO.getPrice());
				newProduct.setProductName(productDTO.getProductName());
				newProduct.setProductImage(productDTO.getImage());
				productReponsitory.updateProductByProductId(newProduct.getProductName(), newProduct.getCategory(),
						newProduct.getProductImage(), newProduct.getProductPrice(), newProduct.getDescription(), id);
				return new OkResponse<String>("Update product successfuly!");
			} else {
				return new NotFoundResponse("Input invali!");
			}
		} else {
			return new NotFoundResponse("Product not found!");
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

// request bao gom categoryId
	@Override
	public BaseResponse getProductByCategoryId(GetProductByIdRequest request) {
		if (request != null && request.getCategoryId() != null) {
			// List<Category> listCategory = categoryRepo.findAll();
			Category category = categoryRepo.findByCategoryId(request.getCategoryId());
			List<Product> listProduct = productReponsitory.findByCategory(category);
//			for(Category category : listCategory) {
//				List<Product> products = category.getProducts();
//				return new OkResponse(products);
//			}
			return new OkResponse(listProduct);
		} else {
			return new NotFoundResponse(LocalMessageUtils.getMessage("category_service_error_try_again"));
		}
		// return new
		// NotFoundResponse(LocalMessageUtils.getMessage("category_service_error_information_invalidate"));
	}

//	@Override
//	public BaseResponse getProductByCategoryId(GetProductByIdRequest request) {
//		if (request != null && request.getCategoryId() != null && request.getPageNumber() != null
//                && request.getPageNumber() > 0) {
//            try {
//                Pageable topTen = PageRequest.of(request.getPageNumber() - 1, Config.PAGER_NUMBER);
//                List<Product> result = productReponsitory.findByCategory(request.getCategoryId(), topTen);
//                return new OkResponse(result);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new NotFoundResponse(LocalMessageUtils.getMessage("customer_service_error_try_again"));
//            }
//
//        } else {
//            return new NotFoundResponse(LocalMessageUtils.getMessage("customer_service_error_information_invalidate"));
//        }
//	}

}
