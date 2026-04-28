package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsersRegistrationDto;
import com.example.demo.service.UsersServiceImpl;

@RestController
@RequestMapping("public/")
public class SignupController {

	@Autowired
	UsersServiceImpl usersServiceImpl;

	@PostMapping("signup")
	ResponseEntity<String> signup(@RequestBody UsersRegistrationDto usersRegistrationDto) {
		usersServiceImpl.userRegisteration(usersRegistrationDto);
		return new ResponseEntity<String>("signup sucessful", HttpStatus.CREATED);
	}

}
