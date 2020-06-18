package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.entity.Role;
import com.example.demo.model.RoleDTO;
import com.example.demo.model.UserDTO;
import com.example.demo.security.jwt.CustomUserDetails;
import com.example.demo.security.jwt.JwtTokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.utils.ConfigUrl;

@RestController
@RequestMapping(ConfigUrl.URL_CUSTOMER) // api/customer
public class UserController {

	@Autowired
	UserService userService;

//	@PostMapping(ConfigUrl.LOGIN)
//	public BaseResponse login(@RequestBody UserDTO userDTO) {
//		return userService.login(userDTO);
//	}

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private UserService mUserService;

	@GetMapping()
	public BaseResponse getAllUser() {
		return mUserService.getAllUser();
	}
	@DeleteMapping(path = "/{id}")
	public BaseResponse deleteUser(@PathVariable("id") int id)  {
		return mUserService.deleteUser(id);
	}
	@PostMapping()
	public BaseResponse addUser(@RequestBody UserDTO userDTO) {
		return mUserService.addUser(userDTO);
	}

	@GetMapping(value="/logout")
	public BaseResponse logout (HttpServletRequest request, HttpServletResponse response) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth != null){
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	  }
	  return new OkResponse("User is logged out");
	}
	
	@PostMapping(ConfigUrl.LOGIN)
	public BaseResponse login(@Valid @RequestBody UserDTO request) {

//		System.out.println("DAC ok2" + request.getName() + request.getPass());
//			// Xác thực từ username và password.

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		String jwt = tokenProvider.generateToken(customUserDetails);
		UserDTO userDTO = new UserDTO();
		userDTO.setToken(jwt);
		String role = "" + customUserDetails.getAuthorities();
		
		List<String> roleName = Arrays.asList(role);
		userDTO.setRoleName(roleName);
		userDTO.setUserName(request.getUserName());
		return new OkResponse(userDTO);

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context

	}

	@PostMapping("/role")
	public BaseResponse addRole(@RequestBody RoleDTO role) {
		return mUserService.addRole(role);
	}
	
	@GetMapping("/role")
	public List<Role> getRole() {
		return mUserService.getAllRole();
	}
	@GetMapping("/admin")
	public String helloAdmin() {
		return "Chi admin moi xem duoc !";
	}

	@GetMapping("/user")
	public String helloUser() {
		return "Chi User moi xem duoc !";
	}

	@GetMapping("/hello")
	public String helloAll() {
		return "Moi nguoi dung deu xem duoc !";
	}

}
