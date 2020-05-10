package com.example.demo.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
@Entity
@Table(name = "order")
public class Order {
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	@Column(name = "order_code")
	private String orderCode;
	@Column(name = "order_date")
	private Date orderDate;
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();

}
