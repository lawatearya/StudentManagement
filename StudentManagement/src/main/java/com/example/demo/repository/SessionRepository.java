package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {
	List<Session> findByStatusAndAttendanceStarted(String status, boolean attendanceStarted);
}
