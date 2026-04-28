package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SessionDto;
import com.example.demo.entity.Batch;
import com.example.demo.entity.Session;
import com.example.demo.entity.SessionQrToken;
import com.example.demo.entity.Trainer;
import com.example.demo.exception.SessionServiceImplException;
import com.example.demo.interfaces.BatchService;
import com.example.demo.interfaces.SessionService;
import com.example.demo.interfaces.TrainerService;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.SessionQrTokenRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.TrainerRepository;
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionQrTokenRepository sessionQrTokenRepository;
	@Autowired
	BatchRepository batchRepository;
	@Autowired
	TrainerRepository trainerRepository;
	@Autowired
	SessionRepository sessionRepository;

    SessionServiceImpl(SessionQrTokenRepository sessionQrTokenRepository) {
        this.sessionQrTokenRepository = sessionQrTokenRepository;
    }

	@Override
	public void createSession(SessionDto dto) {
		Optional<Batch> b = batchRepository.findById(dto.getBatchId());
		if (b.isEmpty()) {
			throw new SessionServiceImplException("Batch not Found", HttpStatus.NOT_FOUND);
		}
		Batch batch = b.get();
		Optional<Trainer> t = trainerRepository.findById(dto.getTrainerId());
		if (t.isEmpty()) {
			throw new SessionServiceImplException("Trainer not Found", HttpStatus.NOT_FOUND);
		}
		Trainer trainer = t.get();

		Session session = new Session();
		session.setTopic(dto.getTopic());
		session.setSessionDate(dto.getSessionDate());
		session.setSessionTime(dto.getSessionTime());
		session.setStatus("SCHEDULED");
		session.setBatch(batch);
		session.setTrainer(trainer);
		sessionRepository.save(session);

	}

	@Override
	public String startSession(int sessionId) {
	    Session session = sessionRepository.findById(sessionId)
	            .orElseThrow(() -> new RuntimeException("Session not found"));

	    session.setStatus("ACTIVE");
	    sessionRepository.save(session);

	    return "Session started successfully";
	}

	@Override
	public String endSession(int sessionId) {
	    Session session = sessionRepository.findById(sessionId)
	            .orElseThrow(() -> new RuntimeException("Session not found"));

	    session.setStatus("COMPLETED");
	    session.setAttendanceStarted(false);
	    sessionRepository.save(session);

	    return "Session ended successfully";
	
	}

}
