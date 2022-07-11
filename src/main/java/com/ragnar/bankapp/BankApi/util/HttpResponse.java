package com.ragnar.bankapp.BankApi.util;

import org.springframework.http.HttpStatus;

public class HttpResponse {

	private String message;

	private HttpStatus httpstatus;

	private int statusCode;

	public HttpResponse(String message, HttpStatus httpstatus, int statusCode) {

		this.message = message;
		this.httpstatus = httpstatus;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

	public void setHttpstatus(HttpStatus httpstatus) {
		this.httpstatus = httpstatus;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
