package com.example.demo.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.base.request.OrderRequest;
import com.example.demo.base.request.ProductRequest;
import com.example.demo.base.response.BaseResponse;
import com.example.demo.base.response.NotFoundResponse;
import com.example.demo.base.response.OkResponse;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderKhachHang;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.reponsitory.OrderKhachHangReponsitory;
import com.example.demo.reponsitory.ProductReponsitory;
import com.example.demo.reponsitory.UserReponsitoty;
import com.example.demo.security.jwt.JwtTokenProvider;
import com.example.demo.service.OrderUserService;
import com.example.demo.utils.StringUtils;

@Service
public class OrderUserServiceImpl implements OrderUserService {
	@Autowired
	ProductReponsitory productReponsitory;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	UserReponsitoty userReponsitory;
	@Autowired
	OrderKhachHangReponsitory khReponsitory;

	@Override
	// rollBack khi gap loi tao don hang
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public BaseResponse addToCart(OrderRequest request, String token) throws Exception {
		if (request != null && request.getListProducts() != null && !request.getListProducts().isEmpty()) {
			OrderKhachHang khachHang = new OrderKhachHang();

			Long sumMoney = 0l;
			// get datetime
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(dt);
			khachHang.setOrderDate(currentTime);
			khachHang.setOrderCode(StringUtils.randomCode());
			List<OrderDetail> listOrderDetail = new ArrayList<OrderDetail>();
			for (ProductRequest productRequest : request.getListProducts()) {

				Product product = productReponsitory.findByProductId(productRequest.getProductId());
				// kiem tra so luong trong kho
				if (product.getQuantity() > productRequest.getQuantity() - 1) {
					sumMoney += product.getProductPrice() * productRequest.getQuantity();
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setEachPrice(product.getProductPrice() * productRequest.getQuantity());
					orderDetail.setQuantity(product.getQuantity());
					orderDetail.setProduct(product);
					orderDetail.setOrderKhachHang(khachHang);
					product.setQuantity(product.getQuantity() - productRequest.getQuantity());
					productReponsitory.save(product);
					listOrderDetail.add(orderDetail);

				} else {
					throw new Exception("So luon san pham " + product.getProductName() + " Khong du trong kho!");
				}

			}
			khachHang.setTotalPrice(sumMoney.intValue());

			try {
				String userCreatedStr = jwtTokenProvider.getUserNameFromJWT(token);

				User userOrder = userReponsitory.findByUserName(userCreatedStr);
				khachHang.setUserId(userOrder.getUserId());
			} catch (Exception e) {
				throw e;
			}

			khachHang.setOrderDetails(listOrderDetail);
			khReponsitory.save(khachHang);
			return new OkResponse<String>("Add to cart successfuly!");
		} else {
			return new NotFoundResponse("Input Invali !");
		}

	}

	@Override
	public BaseResponse removeToCart(int productId) {
		Product product = productReponsitory.findByProductId(productId);
		if (product != null) {

		}
		return null;
	}

	
}
