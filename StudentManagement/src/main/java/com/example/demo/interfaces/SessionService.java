package com.example.demo.interfaces;

import com.example.demo.dto.SessionDto;

public interface SessionService {
	public void createSession(SessionDto dto);

	String startSession(int sessionId);

	String endSession(int sessionId);
}
