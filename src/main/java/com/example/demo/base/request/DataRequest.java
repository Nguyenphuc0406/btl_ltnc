package com.example.demo.base.request;

public class DataRequest<T> {

	private T wsRequest;
	private String token;

	public T getWsRequest() {
		return wsRequest;
	}

	public void setWsRequest(T wsRequest) {
		this.wsRequest = wsRequest;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
