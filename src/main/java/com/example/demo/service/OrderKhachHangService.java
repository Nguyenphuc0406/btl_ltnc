package com.example.demo.service;

import com.example.demo.base.request.DataRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.model.OrderKhachHangDTO;

public interface OrderKhachHangService {
	public BaseResponse payment(DataRequest<OrderKhachHangDTO> dataRequest);
}
