package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.base.request.DataRequest;
import com.example.demo.base.response.BadRequestResponse;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.entity.OrderKhachHang;
import com.example.demo.entity.User;
import com.example.demo.model.OrderKhachHangDTO;
import com.example.demo.model.ProductDTO;
import com.example.demo.reponsitory.UserReponsitoty;
import com.example.demo.service.OrderKhachHangService;
import com.example.demo.utils.StringUtils;

@Service
public class OrderKhachHangServiceImpl implements OrderKhachHangService {
	@Autowired
	UserReponsitoty userReponsitory;

	@Override
	public BaseResponse payment(DataRequest<OrderKhachHangDTO> dataRequest) {

		OrderKhachHangDTO dRequest = dataRequest != null ? dataRequest.getWsRequest() : null;
		if (dRequest != null && dRequest.getOrderCode() != null && dRequest.getLstProduct() != null) {
			OrderKhachHang orderKhachHang = new OrderKhachHang();
			// id nguoi dung
			orderKhachHang.setUserId(dataRequest.getWsRequest().getUserId());

			// get datetime
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(dt);

			orderKhachHang.setOrderCode(StringUtils.randomCode());
			try {
				Integer sumMoney = 0;
				for (ProductDTO productDTO : dataRequest.getWsRequest().getLstProduct()) {
					sumMoney += (productDTO.getQuantity() * productDTO.getPrice());
				}

				orderKhachHang.setTotalPrice(sumMoney);
				orderKhachHang.setOrderDate(currentTime);

//			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			if (principal instanceof UserDetails) {
//				String userName = ((UserDetails) principal).getUsername();
//				User user = userReponsitory.findByUserName(userName);
//				orderKhachHang.setUserId(user.getUserId());
//			} else {
//				String userName = principal.toString();
//			}
				return new OkResponse(orderKhachHang);
			} catch (Exception e) {
				e.printStackTrace();
				return new BadRequestResponse("customer_service_error_information_invalidate");
			}

		} else {
			return new BadRequestResponse("customer_service_error_information_invalidate");
		}

	}
}
