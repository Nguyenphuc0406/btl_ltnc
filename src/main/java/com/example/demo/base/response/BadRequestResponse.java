package com.example.demo.base.response;

import org.springframework.http.HttpStatus;

public class BadRequestResponse extends BaseResponse {

	public BadRequestResponse(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

	public BadRequestResponse() {
		super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

}
