//package com.example.demo.service.impl;
//
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.base.response.BadRequestResponse;
//import com.example.demo.base.response.BaseResponse;
//import com.example.demo.base.response.NotFoundResponse;
//import com.example.demo.base.response.OkResponse;
//import com.example.demo.config.LocalMessageUtils;
//import com.example.demo.entity.Role;
//import com.example.demo.entity.User;
//import com.example.demo.model.UserDTO;
//import com.example.demo.reponsitory.RoleReponsitory;
//import com.example.demo.reponsitory.UserReponsitoty;
//import com.example.demo.security.jwt.CustomUserDetails;
//import com.example.demo.service.UserService;
//
//@Service
//public class UserServiceImpl implements UserDetailsService, UserService {
//	@Autowired
//	UserReponsitoty userReponsitory;
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	RoleReponsitory  roleReponsitory;
//
//	@Autowired
//	public BaseResponse addUser(UserDTO userDTO) {
////		if (userDTO != null && userDTO.getPassword() != null && userDTO.getUserName() != null) {
//////			for (Role r : userDTO.getRoles()) {
//////				List<Role> roles = roleReponsitory.findByRoleId(r);
//////			}
////			List<Role> roles = roleReponsitory.findByRoleId(userDTO.getRoles());
////			if (roles != null) {
////
////				User user = new User();
////				user.setCode(userDTO.getCode());
////				user.setAddress(userDTO.getAddress());
////				user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
////				user.setPhoneNumber(userDTO.getPhoneNumber());
////				user.setUserName(userDTO.getUserName());
//////				user.setRoles(roles);
////				User insertU = userReponsitory.save(user);
//////			Product insertP = productReponsitory.save(newProduct);
////				return insertU != null ? new OkResponse<String>("Add user successfuly!")
////						: new NotFoundResponse("Add user error!");
////
////			} else {
////				return new NotFoundResponse("Role not found!");
////			}
////		} else
////
////		{
//			return new NotFoundResponse("Input invali!");
//
//		}
//	
//
//	@Override
//	public BaseResponse login(UserDTO userDTO) {
//
//		if (userDTO.getPassword() != null && userDTO.getUserName() != null) {
//			User user = userReponsitory.findByUserName(userDTO.getUserName().trim());
//			if (user != null) {
//				if (user.getPassword().trim().endsWith(userDTO.getPassword().trim())) {
//					user.setToken(UUID.randomUUID().toString());
//					try {
//						userReponsitory.save(user);
//						UserDTO dto = new UserDTO();
//						dto.setCode(user.getCode());
//						dto.setAddress(user.getAddress());
//						dto.setPhoneNumber(user.getPhoneNumber());
//						dto.setUserId(user.getUserId());
//						dto.setToken(user.getToken());
//						return new OkResponse(dto);
//					} catch (Exception e) {
//						e.printStackTrace();
//						return new BadRequestResponse(LocalMessageUtils.getMessage("customer_service_error_try_again"));
//					}
//				}
//
//				else {
//					return new BadRequestResponse(
//							LocalMessageUtils.getMessage("customer_service_error_pass_account_incorrect"));
//				}
//			} else {
//				return new BadRequestResponse(LocalMessageUtils.getMessage("customer_service_account_already_exists"));
//			}
//		} else
//
//		{
//			return new BadRequestResponse(LocalMessageUtils.getMessage("customer_service_error_try_again"));
//		}
//
//	}
//
//	@Override
//	public BaseResponse logout(UserDTO userDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) {
//		// Kiểm tra xem user có tồn tại trong database không?
//		User user = userReponsitory.findByUserName(username);
//		System.out.println("Check Load User By Name" + user.getUserName() + user.getPassword());
//		if (user == null) {
//			throw new UsernameNotFoundException(username);
//		}
////			        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
////			       
////			            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
////			        
//		//
////			        return new org.springframework.security.core.userdetails.User(
////			                user.getUserName(), user.getPassword(), grantedAuthorities);
//		return new CustomUserDetails(user);
//	}
//
//}
