package com.example.demo.base.request;

import java.util.List;

public class OrderRequest {
	
	private List<ProductRequest> listProducts;
	
	

	public List<ProductRequest> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<ProductRequest> listProducts) {
		this.listProducts = listProducts;
	}

	

}
