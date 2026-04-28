package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.dto.QrTokenDto;
import com.example.demo.entity.Session;
import com.example.demo.entity.SessionQrToken;
import com.example.demo.interfaces.QrTokenService;
import com.example.demo.repository.SessionQrTokenRepository;
import com.example.demo.repository.SessionRepository;

@Service
public class QrTokenServiceImpl implements QrTokenService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private SessionQrTokenRepository sessionQrTokenRepository;

	private String generateToken(Integer sessionId) {
		return "ATTEND_" + sessionId + "_" + UUID.randomUUID().toString();
	}

	@Override
	public QrTokenDto startAttendance(int sessionId) {

		Optional<Session> s = sessionRepository.findById(sessionId);

		if (s.isEmpty()) {
			throw new RuntimeException("Session not found");
		}

		Session session = s.get();
		if (!"ACTIVE".equals(session.getStatus())) {
			throw new RuntimeException("Session is not active");
		}

		session.setAttendanceStarted(true);
		sessionRepository.save(session);

		Optional<SessionQrToken> qrOptional = sessionQrTokenRepository.findBySessionIdAndActive(sessionId, true);

		SessionQrToken qrToken;

		if (qrOptional.isPresent()) {
			qrToken = qrOptional.get();
		} else {
			qrToken = new SessionQrToken();
			qrToken.setSession(session);
		}

		qrToken.setToken(generateToken(sessionId));
		qrToken.setStartTime(LocalDateTime.now());
		//qrToken.setExpiryTime(LocalDateTime.now().plusSeconds(10));
		qrToken.setExpiryTime(LocalDateTime.now().plusMinutes(2));
		qrToken.setActive(true);

		sessionQrTokenRepository.save(qrToken);

		QrTokenDto dto = new QrTokenDto();
		dto.setSessionId(sessionId);
		dto.setToken(qrToken.getToken());
		dto.setExpiryTime(qrToken.getExpiryTime());

		return dto;
	}

	@Override
	public QrTokenDto getCurrentToken(int sessionId) {

		Optional<SessionQrToken> token = sessionQrTokenRepository.findBySessionIdAndActive(sessionId, true);

		if (token.isEmpty()) {
			throw new RuntimeException("Active QR token not found");
		}

		SessionQrToken qrToken = token.get();

		QrTokenDto dto = new QrTokenDto();
		dto.setSessionId(sessionId);
		dto.setToken(qrToken.getToken());
		dto.setExpiryTime(qrToken.getExpiryTime());

		return dto;
	}

	@Override
	//@Scheduled(fixedRate = 10000)
	@Scheduled(fixedRate = 60000)
	public void refreshActiveSessionTokens() {

		List<Session> activeSessions = sessionRepository.findByStatusAndAttendanceStarted("ACTIVE", true);

		for (Session session : activeSessions) {
			Optional<SessionQrToken> qrOptional = sessionQrTokenRepository.findBySessionIdAndActive(session.getId(),
					true);

			if (qrOptional.isPresent()) {
				SessionQrToken qrToken = qrOptional.get();

				qrToken.setToken(generateToken(session.getId()));
				qrToken.setStartTime(LocalDateTime.now());
				//qrToken.setExpiryTime(LocalDateTime.now().plusSeconds(10));
				qrToken.setExpiryTime(LocalDateTime.now().plusMinutes(2));
				qrToken.setActive(true);

				sessionQrTokenRepository.save(qrToken);
			}
		}
	}

	@Override
	public String stopAttendance(int sessionId) {

		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new RuntimeException("Session not found"));

		if (!"ACTIVE".equals(session.getStatus())) {
			throw new RuntimeException("Session is not active");
		}

		session.setAttendanceStarted(false);
		sessionRepository.save(session);

		Optional<SessionQrToken> qrOptional = sessionQrTokenRepository.findBySessionIdAndActive(sessionId, true);

		if (qrOptional.isPresent()) {
			SessionQrToken qrToken = qrOptional.get();
			qrToken.setActive(false);
			sessionQrTokenRepository.save(qrToken);
		}

		return "Attendance ended successfully";
	}
}