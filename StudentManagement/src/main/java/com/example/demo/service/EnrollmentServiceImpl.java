package com.example.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EnrollmentDto;
import com.example.demo.entity.Batch;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Trainer;
import com.example.demo.entity.Users;
import com.example.demo.exception.EnrollmentServiceImplException;
import com.example.demo.interfaces.EnrollmentService;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	BatchRepository batchRepository;
	
	@Autowired
	TrainerRepository trainerRepository;

	@Override
	public void cancelEnrollment(int eId) {
		Optional<Enrollment> enroll = enrollmentRepository.findById(eId);
		if (enroll.isEmpty()) {
			throw new EnrollmentServiceImplException("Enrollment not found", HttpStatus.NOT_FOUND);
		}
		Enrollment exist = enroll.get();
		exist.setStatus("Cancelled");
		enrollmentRepository.save(exist);

	}
	
	@Override
	public void deleteEnrollment(int eId) {
		Optional<Enrollment> enroll = enrollmentRepository.findById(eId);
		if(enroll.isEmpty()) {
			throw new EnrollmentServiceImplException("Enrollment not found",HttpStatus.NOT_FOUND);
		}
		Enrollment e = enroll.get();
		enrollmentRepository.delete(e);
		
	}
	
	@Override
	public void updateEnrollment(int eId, EnrollmentDto dto) {
		Optional<Enrollment> enroll = enrollmentRepository.findById(eId);
		if (enroll.isEmpty()) {
			throw new EnrollmentServiceImplException("Enrollment is not found ", HttpStatus.NOT_FOUND);
		}
		Enrollment enrollment = enroll.get();
		enrollment.setDate(dto.getDate());
		enrollment.setMode(dto.getMode());
		enrollment.setStatus(dto.getStatus());
		enrollment.setVideoAccess(dto.getVideoAccess());
		enrollment.setTotalFees(dto.getTotalFees());
		enrollment.setPaidFees(dto.getPaidFees());
		enrollment.setNextInstallment(dto.getNextInstallment());
		enrollment.setDueDate(dto.getDueDate());
		enrollment.setExtendedDueDate(dto.getExtendedDueDate());
		try {
			enrollmentRepository.save(enrollment);
		} catch (Exception e) {
			throw new EnrollmentServiceImplException("Enrollment not update", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public void enrollByStudent(EnrollmentDto dto) {
	Optional<Users> u = usersRepository.findById(dto.getUserId()); 
	if(u.isEmpty()) {
		throw new EnrollmentServiceImplException("User Not Found", HttpStatus.NOT_FOUND);
	}
	Users user = u.get();
	Optional<Batch> b  = batchRepository.findById(dto.getBatchId()); 
	if(u.isEmpty()) {
		throw new EnrollmentServiceImplException("Batch Not Found", HttpStatus.NOT_FOUND);
	}
	Batch batch = b.get();
	
	Enrollment enrollment = new Enrollment();
	enrollment.setDate(dto.getDate());
	enrollment.setMode(dto.getMode());
	enrollment.setStatus(dto.getStatus());
	enrollment.setVideoAccess(dto.getVideoAccess());
	enrollment.setTotalFees(dto.getTotalFees());
	enrollment.setPaidFees(dto.getPaidFees());
	enrollment.setNextInstallment(dto.getNextInstallment());
	enrollment.setDueDate(dto.getDueDate());
	enrollment.setExtendedDueDate(dto.getExtendedDueDate());
	enrollment.setPaidFees(dto.getPaidFees());
	enrollment.setBatch(batch);
	enrollment.setUser(user);
	enrollmentRepository.save(enrollment);
	
		
	}
	

}
