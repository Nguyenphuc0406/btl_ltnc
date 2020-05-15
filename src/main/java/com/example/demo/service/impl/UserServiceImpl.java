package com.example.demo.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.base.response.BadRequestResponse;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.config.LocalMessageUtils;
import com.example.demo.entity.User;
import com.example.demo.model.UserDTO;
import com.example.demo.reponsitory.UserReponsitoty;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserReponsitoty userReponsitory;

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

}
