package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.QrTokenDto;
import com.example.demo.interfaces.AttendanceService;

@RestController
@RequestMapping("trainer")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/user/markattendance")
	public ResponseEntity<String> markAttendance(@RequestBody QrTokenDto dto) {
		return new ResponseEntity(attendanceService.markAttendance(dto),HttpStatus.OK);
	}
}