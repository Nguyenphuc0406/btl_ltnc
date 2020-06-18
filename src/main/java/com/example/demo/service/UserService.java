package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.model.RoleDTO;
import com.example.demo.model.UserDTO;

public interface UserService {
	public BaseResponse addUser(UserDTO userDTO);

	public BaseResponse login(UserDTO userDTO);

	public BaseResponse logout(UserDTO userDTO);

	public UserDetails loadUserByUsername(String username);
//	public BaseResponse getListMenu();
	
	public BaseResponse addRole(RoleDTO roleDTO);
	
	public List<Role> getAllRole();
	
	public BaseResponse getAllUser();
	
	public BaseResponse deleteUser(int id);
	
	public BaseResponse getUser(int id);
}
