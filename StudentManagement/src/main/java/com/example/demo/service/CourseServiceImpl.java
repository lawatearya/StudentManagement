package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;
import com.example.demo.exception.CourseServiceImplException;
import com.example.demo.interfaces.CourseService;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Override
	public void addCourse(CourseDto courseDto) {

		if (courseDto.getCourseName() == null || courseDto.getCourseName().isEmpty()) {
			throw new CourseServiceImplException("Course name is required", HttpStatus.BAD_REQUEST);
		}

		if (courseDto.getCourseDuration() == null || courseDto.getCourseDuration().isEmpty()) {
			throw new CourseServiceImplException("Course duration is required", HttpStatus.BAD_REQUEST);
		}
		if (courseDto.getStatus() == null || courseDto.getStatus().isEmpty()) {
			throw new CourseServiceImplException("Status is required", HttpStatus.BAD_REQUEST);
		}
		Course course = new Course();

		course.setCourseName(courseDto.getCourseName());
		course.setCourseDuration(courseDto.getCourseDuration());
		course.setSyllabus_link(courseDto.getSyllabus_link());
		course.setStatus(courseDto.getStatus());
		course.setPrerequsit(courseDto.getPrerequsit());

		try {
			courseRepository.save(course);
		} catch (Exception e) {
			throw new CourseServiceImplException("Unable to save course", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteCourse(int id) {
		if (!courseRepository.existsById(id)) {
			throw new CourseServiceImplException("Course not found with id: " + id, HttpStatus.NOT_FOUND);
		}
		courseRepository.deleteById(id);
	}

	@Override
	public void deleteAllCourse() {
		courseRepository.deleteAll();
	}

	@Override
	public Course getCourse(int id) {
		Optional<Course> o = courseRepository.findById(id);
		if (o.isEmpty()) {
			throw new CourseServiceImplException("Course not found", HttpStatus.NOT_FOUND);
		}
		return o.get();
	}

	@Override
	public List<Course> getAllCourse() {
		return courseRepository.findAll();
	}

}
