package com.example.demo.interfaces;

import com.example.demo.dto.QrTokenDto;


public interface QrTokenService {
	QrTokenDto startAttendance(int sessionId);

	QrTokenDto getCurrentToken(int sessionId);

	void refreshActiveSessionTokens();

	String stopAttendance(int sessionId);
}
