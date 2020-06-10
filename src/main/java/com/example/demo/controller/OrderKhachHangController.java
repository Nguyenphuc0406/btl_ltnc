package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.request.DataRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.model.OrderKhachHangDTO;
import com.example.demo.service.OrderKhachHangService;
import com.example.demo.utils.ConfigUrl;

@RestController
@RequestMapping(ConfigUrl.PAY_MENT)
public class OrderKhachHangController {

	@Autowired
	OrderKhachHangService khService;

	@PostMapping
	public BaseResponse payment(@RequestBody DataRequest<OrderKhachHangDTO> dataRequest) {
		return khService.payment(dataRequest);
	}
}
