package com.example.demo.model;

import com.example.demo.entity.OrderKhachHang;
import com.example.demo.entity.Product;

public class OrderDetailDTO {

	private int orderDetailId;

	private Product product;

	private int quantity;

	private int eachPrice;

	private OrderKhachHang order;

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getEachPrice() {
		return eachPrice;
	}

	public void setEachPrice(int eachPrice) {
		this.eachPrice = eachPrice;
	}

	public OrderKhachHang getOrder() {
		return order;
	}

	public void setOrder(OrderKhachHang order) {
		this.order = order;
	}

}
