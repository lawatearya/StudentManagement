package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	
	List<Enrollment> findByBatchId(long batchId);
	List<Enrollment> findByStatusAndConfEmail(String status,boolean confEmail);
	boolean existsByUserIdAndBatchId(Long userId, Integer batchId);

}
