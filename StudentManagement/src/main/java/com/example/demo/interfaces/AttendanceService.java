package com.example.demo.interfaces;

import com.example.demo.dto.QrTokenDto;

public interface AttendanceService {
	String markAttendance(QrTokenDto dto);
}