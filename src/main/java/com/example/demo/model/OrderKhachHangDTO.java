package com.example.demo.model;

import java.sql.Date;
import java.util.List;

public class OrderKhachHangDTO {
	private String orderCode;
	private String orderDate;
	private int userId;
	private int totalPrice;
	private List<OrderDetailDTO> detailDTOs;
	private List<ProductDTO> lstProduct;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public List<ProductDTO> getLstProduct() {
		return lstProduct;
	}

	public void setLstProduct(List<ProductDTO> lstProduct) {
		this.lstProduct = lstProduct;
	}

	public List<OrderDetailDTO> getDetailDTOs() {
		return detailDTOs;
	}

	public void setDetailDTOs(List<OrderDetailDTO> detailDTOs) {
		this.detailDTOs = detailDTOs;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
