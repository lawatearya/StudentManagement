package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EnrollmentDto;
import com.example.demo.interfaces.EnrollmentService;

@RestController
@RequestMapping("/admin")
public class EnrollmentController {
	@Autowired
	EnrollmentService enrollmentService;

	@PatchMapping("enrollment/{eId}")
	public ResponseEntity<String> cancelEnrollment(@PathVariable int eId) {
		enrollmentService.cancelEnrollment(eId);
		return new ResponseEntity("Enrollment Cancelled", HttpStatus.OK);
	}
	
	@DeleteMapping("enrollment/{eId}")
	public ResponseEntity<String> deleteEnrollment(@PathVariable int eId) {
		enrollmentService.deleteEnrollment(eId);
		return new ResponseEntity("Enrollment Deleted", HttpStatus.OK);
	}
	
	@PutMapping("enrollment/{eId}")
	public ResponseEntity updateEnrollment(@PathVariable int eId,@RequestBody EnrollmentDto e) {
		enrollmentService.updateEnrollment(eId, e);
		return new ResponseEntity("Enrollment is updated", HttpStatus.OK);
	}
	@PostMapping("/enrollment")
	public ResponseEntity<String> enrollByStudent(@RequestBody EnrollmentDto dto){
		enrollmentService.enrollByStudent(dto);
		return new ResponseEntity("Enrollment Successfully",HttpStatus.OK);
	}
}
