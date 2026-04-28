package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Users;
import com.example.demo.interfaces.UsersService;

@RestController

public class UserController {
	@Autowired
	UsersService service;

	@GetMapping("/hit")
	ResponseEntity<String> user() {
		return new ResponseEntity<String>("Logged in as user", HttpStatus.OK);
	}

	@GetMapping("user/{uId}")
	public ResponseEntity<Users> getUserById(@PathVariable int uId) {
		return new ResponseEntity(service.getUserById(uId), HttpStatus.FOUND);
	}

	@DeleteMapping("/admin/user/{uId}")
	public ResponseEntity<Users> deleteUser(@PathVariable int uId) {
		service.deleteUserById(uId);
		return new ResponseEntity("User is Deleted", HttpStatus.OK);
	}

	@GetMapping("/admin/users")
	public ResponseEntity<List<Users>> getUsers() {
		return new ResponseEntity(service.getUsers(), HttpStatus.FOUND);
	}

	@PutMapping("user/{uId}")
	public ResponseEntity<Users> updateUser(@RequestBody Users u, @PathVariable int uId) {
		return new ResponseEntity(service.updateUserById(u, uId), HttpStatus.OK);
	}

}
