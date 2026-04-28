package com.example.demo.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.BatchServiceImplException;
import com.example.demo.exception.CourseServiceImplException;
import com.example.demo.exception.EnrollmentServiceImplException;
import com.example.demo.exception.SessionServiceImplException;
import com.example.demo.exception.TrainerServiceImplException;
import com.example.demo.exception.UsersServiceException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = UsersServiceException.class)
	ResponseEntity<String> handleUsersServiceException(UsersServiceException use) {
		return new ResponseEntity<String>(use.getMessage(), use.getHttpStatus());
	}
	
	@ExceptionHandler(value = TrainerServiceImplException.class)
	ResponseEntity<String> handleTrainerServiceException(TrainerServiceImplException tse) {
		return new ResponseEntity<String>(tse.getMessage(), tse.getHttpStatus());
	}
	
	@ExceptionHandler(value = BatchServiceImplException.class)
	ResponseEntity<String> handleBatchServiceImplException(BatchServiceImplException bse) {
		return new ResponseEntity<String>(bse.getMessage(), bse.getHttpStatus());
	}
	
	@ExceptionHandler(value = CourseServiceImplException.class)
	ResponseEntity<String> handleCourseServiceImplException(CourseServiceImplException cse) {
		return new ResponseEntity<String>(cse.getMessage(), cse.getHttpStatus());
	}

	@ExceptionHandler(value = EnrollmentServiceImplException.class)
	ResponseEntity<String> handleEnrollementServiceImplException(EnrollmentServiceImplException ese) {
		return new ResponseEntity<String>(ese.getMessage(), ese.getHttpStatus());
	}
	@ExceptionHandler(value = SessionServiceImplException.class)
	ResponseEntity<String> handleSessionServiceImplException(SessionServiceImplException ese) {
		return new ResponseEntity<String>(ese.getMessage(), ese.getHttpStatus());
	}

	@ExceptionHandler(value = Exception.class)
	ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
