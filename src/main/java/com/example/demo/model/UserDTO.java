package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserDTO {
	//JsonInclude : chi dua ra nhung tham so not null
	
	@JsonInclude(value = Include.NON_NULL)
	private Integer userId;
	@JsonInclude(value = Include.NON_NULL)
	private String code;
	@JsonInclude(value = Include.NON_NULL)
	private String userName;
	@JsonInclude(value = Include.NON_NULL)
	private String password;
	@JsonInclude(value = Include.NON_NULL)
	private String phoneNumber;
	@JsonInclude(value = Include.NON_NULL)
	private String token;
	@JsonInclude(value = Include.NON_NULL)
	private String address;
//	private Role roles;
	@JsonInclude(value = Include.NON_NULL)
	private List<String> roleName;

	public List<String> getRoleName() {
		return roleName;
	}

	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public Role getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Role roles) {
//		this.roles = roles;
//	}



}
