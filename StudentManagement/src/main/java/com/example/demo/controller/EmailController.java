package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SendOtpService;

@RestController
@RequestMapping("public/")
public class EmailController {
	@Autowired
	SendOtpService otpservice;

	@PostMapping("sendOtp")
	public ResponseEntity<String> sendOtp(@RequestParam String email) {
		otpservice.sendOtp(email);
		return new ResponseEntity("OTP send successfully", HttpStatus.OK);
	}

	@PostMapping("verifyOtp")
	public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {

		String result = otpservice.verifyOtp(email, otp);

		return new ResponseEntity(result, HttpStatus.OK);
	}
}
