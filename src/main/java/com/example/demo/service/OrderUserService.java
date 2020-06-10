package com.example.demo.service;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.base.request.OrderRequest;
import com.example.demo.base.response.BaseResponse;

public interface OrderUserService {
	@Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class)
	public BaseResponse addToCart(OrderRequest request, String token) throws Exception;

	public BaseResponse removeToCart(int productId);

}
