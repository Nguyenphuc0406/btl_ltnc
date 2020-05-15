package com.example.demo.service;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.model.UserDTO;

public interface UserService {
	public BaseResponse login(UserDTO userDTO);

	public BaseResponse logout(UserDTO userDTO);

//	public BaseResponse getListMenu();

}
