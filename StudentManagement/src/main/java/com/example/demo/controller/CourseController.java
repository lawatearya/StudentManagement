package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;
import com.example.demo.interfaces.CourseService;

@RestController
@RequestMapping("/admin")
public class CourseController {

	@Autowired
	CourseService courseService;

	@PostMapping("/addCourse")
	public ResponseEntity<String> addCourse(@RequestBody CourseDto courseDto) {
		courseService.addCourse(courseDto);
		return new ResponseEntity<>("Course added successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable int id) {
		courseService.deleteCourse(id);
		return new ResponseEntity<>("Course deleted", HttpStatus.OK);
	}

	@DeleteMapping("/deleteAllCourses")
	public ResponseEntity<String> deleteAllCourse() {
		courseService.deleteAllCourse();
		return new ResponseEntity<>("All Courses deleted", HttpStatus.OK);
	}

	@GetMapping("/getCourse/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable int id) {
		return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
	}

	@GetMapping("/getAllCourses")
	public ResponseEntity<List<Course>> getAllCourse() {
		return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
	}
}
