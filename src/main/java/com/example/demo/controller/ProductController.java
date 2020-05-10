package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.data.ProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductDTO;
import com.example.demo.service.ProductService;
import com.example.demo.utils.ConfigUrl;

@RestController
@RequestMapping(ConfigUrl.URL_PRODUCT)
public class ProductController {
	@Autowired
	ProductService productservice;

	@GetMapping(ConfigUrl.GET_PRODUCT_NEW)
	public List<Product> getAllProduct() {
		return productservice.getAllProduct();
	}

	@PostMapping(ConfigUrl.ADD_PRODUCT_NEW)
	public BaseResponse addProduct(@RequestBody ProductDTO productDTO) {
		return productservice.addProduct(productDTO);
	}
	@PutMapping(ConfigUrl.UPDATE_PRODUCT)
	public BaseResponse updateProduct(@RequestBody ProductDTO productDTO) {
		return productservice.updateProduct(productDTO);
	}
	@DeleteMapping(ConfigUrl.DELETE_PRODUCT_BY_ID)
	public BaseResponse deleteProduct(@PathVariable ("id") int productId) {
		return productservice.deleteProduct(productId);
	}
//   @PostMapping(ConfigUrl.GET_PRODUCT_BY_ID)
//	    public BaseResponse getProductById(@RequestBody GetProductByIdRequest request){
//	        return mProductService.getProductById(request);
//	    }

}
