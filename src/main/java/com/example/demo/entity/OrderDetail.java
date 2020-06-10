package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@Column(name = "order_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailId;
// @JsonIgnore
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "each_price")
	private int eachPrice;
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private OrderKhachHang orderKhachHang;

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

	public OrderKhachHang getOrderKhachHang() {
		return orderKhachHang;
	}

	public void setOrderKhachHang(OrderKhachHang orderKhachHang) {
		this.orderKhachHang = orderKhachHang;
	}



}
