package com.example.demo.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.request.OrderRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.NotFoundResponse;
import com.example.demo.service.OrderUserService;
import com.example.demo.utils.ConfigUrl;

@RestController
@RequestMapping(ConfigUrl.CREATE_ORDER)
public class OrderUserController {
	@Autowired
	OrderUserService orderUserService;

	@PostMapping(path = "/{userName}")

	public BaseResponse addToCart(@RequestBody OrderRequest orderRequest,@PathVariable("userName") String userName) {
		try {
			return orderUserService.addToCart(orderRequest, userName);
		} catch (Exception e) {
			// TODO: handle exception
			return new NotFoundResponse(e.getMessage());
		}

	}
}
