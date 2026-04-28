package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtil;
import com.example.demo.service.userDetailServiceImpl;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class LoginController {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	userDetailServiceImpl usersDetailServiceImpl;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("login/{username}/{password}")
	ResponseEntity<Map<String, Object>> register(@PathVariable String username, @PathVariable String password) {

		UserDetails userDetails = usersDetailServiceImpl.loadUserByUsername(username);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		Map<String, Object> r = new HashMap<>();
		r.put("Token", jwtUtil.generateToken(userDetails));
		r.put("Roles", userDetails.getAuthorities().stream().map(a -> a.getAuthority()).toList());

		return new ResponseEntity<>(r, HttpStatus.OK);
	}
}
