package com.example.demo.utils;

public interface ConfigUrl {
//  parent
	String URL_API = "/api";
	String URL_CUSTOMER = URL_API + "/customer";
	String URL_PRODUCT = URL_API + "/product";
	String URL_ORDER = URL_API + "/order";
	String URL_USER = URL_API + "/user";
	String URL_CONFIG = URL_API + "/config";
	String URL_ROLES = URL_API + "/roles";

//  child
	String GET_PRODUCT_NEW = "/getProduct";
	String GET_PRODUCT_BY_ID = "/getProductById";
	String GET_ALL_PRODUCT_TYPE = "/getAllProductType";
	String ADD_PRODUCT_NEW = "/addProductNew";
	String UPDATE_PRODUCT= "/updateProduct";
	String DELETE_PRODUCT_BY_ID = "/product/{id}";

	String CREATE_NEW_CUSTOMER = "/createNewCustomer";
	String LOGIN = "/login";
	String LOGOUT = "/logout";
	String UPLOAD_IMAGE = "/uploadImage";
	String UPLOAD_IMAGE2 = "/uploadImage2";

	String CREATE_ORDER = "/createOrder";
	String GET_ORDER_BY_CUS_ID = "/getOrderByCusId";
	String GET_ORDER_NEW_BY_USER_ID = "/getOrderNewByUserID";
	String GET_ORDER_PACKING = "/getOrderPackaging";
	String GET_ORDER_BY_SHIP = "/findOrdersByShip";
	String STAFF_PENDING_ORDER = "/staffPendingOrder";
	String STAFF_PACK_ORDER = "/staffPackOrder";
	String SHIP_RECEIPT_ORDER = "/shipReceiptOrder";
	String SHIP_DELIVERY_SUCCESS = "/shipDeliverySuccess";

	String LOGIN_SYSTEM = "/loginSystem";
	String GET_LIST_MENU = "/getListMenu";
	String LOGOUT_SYSTEM = "/logoutSystem";

	String GET_ALL_ROLE = "/getAllRoles";
	String GET_ALL_MENU = "/getAllMenu";
	String CREATE_MENU = "/createMenu";

	String GET_LIST_IMAGE_HOME_APP_CLIENT = "/getListImageHomeAppClient";
}
