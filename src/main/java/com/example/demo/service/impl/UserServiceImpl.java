package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.base.response.BadRequestResponse;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.NotFoundResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.config.LocalMessageUtils;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.model.ProductDTO;
import com.example.demo.model.RoleDTO;
import com.example.demo.model.UserDTO;
import com.example.demo.reponsitory.RoleReponsitory;
import com.example.demo.reponsitory.UserReponsitoty;
import com.example.demo.security.jwt.CustomUserDetails;
import com.example.demo.security.jwt.JwtTokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.utils.RoleType;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	@Autowired
	UserReponsitoty userReponsitory;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleReponsitory roleReponsitory;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Override
	public BaseResponse addUser(UserDTO userDTO) {
//		User insertU = userReponsitory.save(userDTO);
//		return insertU != null ? new OkResponse<String>("Add user successfuly!")
//			: new NotFoundResponse("Add user error!");
		if (userDTO != null && userDTO.getPassword() != null && userDTO.getUserName() != null
				&& userDTO.getRoleName() != null && !userDTO.getRoleName().isEmpty()) {

			User userCheck = userReponsitory.findByUserName(userDTO.getUserName());

			if (userCheck == null) {

				User userCreated = null;
				try {

//					String userCreatedStr = jwtTokenProvider.getUserNameFromJWT(token);
//					System.out.println(userCreatedStr + " Phuc");
//					userCreated = userReponsitory.findByUserName(userCreatedStr);

//					List<String> roleCreated = new ArrayList<String>();
//					for (Role role : userCreated.getRoles()) {
//						roleCreated.add(role.getRoleName());
//					}
//
//					int roleNumberCreated = getMaxRoleType(roleCreated);
//					int roleNew = getMaxRoleType(userDTO.getRoleName());

						User userInserted = new User();
						userInserted.setCode(userDTO.getCode());
						userInserted.setUserName(userDTO.getUserName());
						userInserted.setPassword(passwordEncoder.encode(userDTO.getPassword()));
						userInserted.setAddress(userDTO.getAddress());
						userInserted.setPhoneNumber(userDTO.getPhoneNumber());
						//set role
						Set<Role> roleInserted = new HashSet<>();
						for(String roleName : userDTO.getRoleName()) {
							try {
								roleInserted.add(roleReponsitory.findByRoleName(roleName));
							}catch (Exception e) {
								// TODO: handle exception
							}
							
						}
						userInserted.setRoles(roleInserted);
						userReponsitory.save(userInserted);
						
						return new OkResponse("Duoc tao user");
				} catch (Exception e) {
					return new NotFoundResponse("Error");

				}

			}

			else {
				return new NotFoundResponse("User da ton tai!");
			}
		}
		return new NotFoundResponse("Input invali!");

	}

	private int getMaxRoleType(List<String> roles) {
		int roleType = 0;
		for (String role : roles) {
			switch (role) {
			case RoleType.ADMIN:
				roleType = 4;
				break;
			case RoleType.USER:
				if (roleType < 3) {
					roleType = 3;
				}
				break;
			case RoleType.USER_LEVER_1:
				if (roleType < 2) {
					roleType = 2;
				}
				break;
			case RoleType.USER_LEVER_2:
				if (roleType < 1) {
					roleType = 1;
				}
				break;
			default:

				break;
			}
			if (roleType == 4) {
				break;
			}

		}
		return roleType;
	}

	@Override
	public BaseResponse login(UserDTO userDTO) {

		if (userDTO.getPassword() != null && userDTO.getUserName() != null) {
			User user = userReponsitory.findByUserName(userDTO.getUserName().trim());
			if (user != null) {
				if (user.getPassword().trim().endsWith(userDTO.getPassword().trim())) {
					user.setToken(UUID.randomUUID().toString());
					try {
						userReponsitory.save(user);
						UserDTO dto = new UserDTO();
						dto.setCode(user.getCode());
						dto.setAddress(user.getAddress());
						dto.setPhoneNumber(user.getPhoneNumber());
						dto.setUserId(user.getUserId());
						dto.setToken(user.getToken());
						return new OkResponse(dto);
					} catch (Exception e) {
						e.printStackTrace();
						return new BadRequestResponse(LocalMessageUtils.getMessage("customer_service_error_try_again"));
					}
				}

				else {
					return new BadRequestResponse(
							LocalMessageUtils.getMessage("customer_service_error_pass_account_incorrect"));
				}
			} else {
				return new BadRequestResponse(LocalMessageUtils.getMessage("customer_service_account_already_exists"));
			}
		} else

		{
			return new BadRequestResponse(LocalMessageUtils.getMessage("customer_service_error_try_again"));
		}

	}

	@Override
	public BaseResponse logout(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// Kiểm tra xem user có tồn tại trong database không?
		User user = userReponsitory.findByUserName(username);
		System.out.println("Check Load User By Name" + user.getUserName() + user.getPassword());
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
//			        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//			       
//			            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
//			        
		//
//			        return (UserDetails) new User(
//			                user.getUserName(), user.getPassword(), grantedAuthorities);
		return new CustomUserDetails(user);
	}

	// JWTAuthenticationFilter sẽ sử dụng hàm này
	@Transactional
	public UserDetails loadUserById(int id) {
		User user = userReponsitory.findByUserId(id);
		if (user != null) {
			return new CustomUserDetails(user);
		} else {
			return (UserDetails) new UsernameNotFoundException("User not found with id : " + id);
		}

	}

	@Override
	public BaseResponse addRole(RoleDTO roleDTO) {
		Role role = new Role();
		role.setDescription(roleDTO.getDescription());
		role.setRoleName(roleDTO.getRoleName());
		roleReponsitory.save(role);
		return null;
	}

	@Override
	public List<Role> getAllRole() {
		
		return roleReponsitory.findAll();
	}

	@Override
	public BaseResponse getAllUser() {
		List<User> users = userReponsitory.findAll();
		List<UserDTO>userDTOs = new ArrayList<>();
		if (users != null) {

			for (User user : users) {
				UserDTO dto = new UserDTO();
				dto.setUserId(user.getUserId());	
				dto.setAddress(user.getAddress());
				dto.setUserId(user.getUserId());
				dto.setUserName(user.getUserName());
				dto.setCode(user.getCode());
				dto.setPassword(user.getPassword());
				dto.setPhoneNumber(user.getPhoneNumber());
				userDTOs.add(dto);

			}
			return new OkResponse(userDTOs);
		} else {
			return new NotFoundResponse("Product not found!");
		}
	}

	@Override
	public BaseResponse deleteUser(int id) {
		User user = userReponsitory.findByUserId(id);
		if (user != null) {
			userReponsitory.deleteById(id);

			return new OkResponse<String>("Delete user successfuly!");

		} else {
			return new NotFoundResponse("User not found!");

		}
	}

	@Override
	public BaseResponse getUser(int id) {
		User user = userReponsitory.findByUserId(id);
		
		if (user != null) {
			UserDTO dto = new UserDTO();
			dto.setAddress(user.getAddress());
			dto.setCode(user.getCode());
			dto.setPassword(user.getPassword());
			dto.setPhoneNumber(user.getPhoneNumber());
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());
			return new OkResponse(dto);
		} else {
			return new NotFoundResponse("Product not found!");
		}
	}

}
