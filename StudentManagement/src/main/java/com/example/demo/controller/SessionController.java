package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SessionDto;
import com.example.demo.interfaces.SessionService;

@RestController
@RequestMapping("trainer")
public class SessionController {
@Autowired
SessionService sessionService;
	@PostMapping("/session")
	public ResponseEntity<String> createSession(@RequestBody SessionDto dto) {
		sessionService.createSession(dto);
		return new ResponseEntity("Session is Created", HttpStatus.CREATED);
	}
	@PostMapping("/session/start/{sessionId}")
	public ResponseEntity<String> startSession(@PathVariable int sessionId) {
	    return new ResponseEntity(sessionService.startSession(sessionId),HttpStatus.OK);
	}

	@PostMapping("/session/end/{sessionId}")
	public ResponseEntity<String> endSession(@PathVariable Integer sessionId) {
	    return new ResponseEntity(sessionService.endSession(sessionId),HttpStatus.OK);
	}
}
