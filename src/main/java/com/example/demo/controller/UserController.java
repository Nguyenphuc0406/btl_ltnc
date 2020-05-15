package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.model.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.utils.ConfigUrl;

@RestController
@RequestMapping(ConfigUrl.URL_CUSTOMER)
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(ConfigUrl.LOGIN)
	public BaseResponse login(@RequestBody UserDTO userDTO) {
		return userService.login(userDTO);
	}

}
