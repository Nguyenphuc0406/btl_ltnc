package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.request.OrderRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.NotFoundResponse;
import com.example.demo.service.OrderUserService;
import com.example.demo.utils.ConfigUrl;

@RestController

public class OrderUserController {
	@Autowired
	OrderUserService orderUserService;

	@PostMapping(ConfigUrl.CREATE_ORDER)

	public BaseResponse addToCart(@RequestBody OrderRequest orderRequest, @RequestHeader("accept-token") String token) {
		try {
			return orderUserService.addToCart(orderRequest, token);
		} catch (Exception e) {
			// TODO: handle exception
			return new NotFoundResponse(e.getMessage());
		}

	}
}
