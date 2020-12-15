package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.entity.User;
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

	@PostMapping()
	public BaseResponse addUser(@RequestBody UserDTO userDTO, @RequestHeader("accept-token") String token) {
		return mUserService.addUser(userDTO, token);
	}
//	@PostMapping()
//	public BaseResponse addUser(@RequestBody UserDTO userDTO) {
//		return mUserService.addUser(userDTO);
//	}

	@PostMapping(ConfigUrl.LOGIN)
	public BaseResponse login(@Valid @RequestBody UserDTO request) {

//			// Xác thực từ username và password.

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		UserDTO userDTO = new UserDTO();
		userDTO.setToken(jwt);
		return new OkResponse(userDTO);

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context

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
