package com.example.demo.interfaces;

import com.example.demo.dto.EnrollmentDto;

public interface EnrollmentService {
	public void cancelEnrollment(int eId);
	public void deleteEnrollment(int eId);
	public void updateEnrollment(int eId,EnrollmentDto dto);
	public void enrollByStudent(EnrollmentDto dto);
	
}
