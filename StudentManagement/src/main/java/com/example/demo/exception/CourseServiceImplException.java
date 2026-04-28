package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class CourseServiceImplException extends RuntimeException {
	
	private String message;
	private HttpStatus httpStatus;

	public CourseServiceImplException (String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	

}
