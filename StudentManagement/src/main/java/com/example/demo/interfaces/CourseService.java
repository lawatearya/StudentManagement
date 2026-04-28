package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;

public interface CourseService {
	
		void addCourse(CourseDto courseDto);

		void deleteCourse(int id);

		void deleteAllCourse();

		Course getCourse(int id);

		List<Course> getAllCourse();

	}


