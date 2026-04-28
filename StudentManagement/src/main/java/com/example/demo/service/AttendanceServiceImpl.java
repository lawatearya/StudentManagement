package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.QrTokenDto;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Session;
import com.example.demo.entity.SessionQrToken;
import com.example.demo.entity.Users;
import com.example.demo.interfaces.AttendanceService;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.SessionQrTokenRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private SessionQrTokenRepository sessionQrTokenRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public String markAttendance(QrTokenDto dto) {

		Optional<SessionQrToken> qrTokenOptional = sessionQrTokenRepository.findByTokenAndActive(dto.getToken(), true);

		if (qrTokenOptional.isEmpty()) {
			throw new RuntimeException("Invalid QR token");
		}

		SessionQrToken qrToken = qrTokenOptional.get();

		if (qrToken.getExpiryTime().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("QR token expired");
		}

		Session session = qrToken.getSession();

		if (!"ACTIVE".equals(session.getStatus())) {
			throw new RuntimeException("Session is not active");
		}
		
		if (!session.isAttendanceStarted()) {
			throw new RuntimeException("Attendance is not started");
		}

		Optional<Users> u = usersRepository.findById(dto.getUserId());

		if (u.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		Users user = u.get();

		boolean belongsToBatch = enrollmentRepository.existsByUserIdAndBatchId(user.getId(),
				session.getBatch().getId());

		if (!belongsToBatch) {
			throw new RuntimeException("Student does not belong to this batch");
		}

		boolean alreadyMarked = attendanceRepository.existsBySessionIdAndUserId(session.getId(), user.getId());

		if (alreadyMarked) {
			throw new RuntimeException("Attendance already marked");
		}

		Attendance attendance = new Attendance();
		attendance.setAttendanceTime(LocalDateTime.now());
		attendance.setStatus("PRESENT");
		attendance.setSession(session);
		attendance.setUser(user);

		attendanceRepository.save(attendance);

		return "Attendance marked successfully";
	}
}