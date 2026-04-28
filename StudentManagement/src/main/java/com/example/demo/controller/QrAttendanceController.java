package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.QrTokenDto;

import com.example.demo.interfaces.QrTokenService;

@RestController
@RequestMapping("/trainer")
public class QrAttendanceController {

    @Autowired
    private QrTokenService qrTokenService;

    @PostMapping("/start_attendance/{sessionId}")
    public ResponseEntity<QrTokenDto> startAttendance(@PathVariable Integer sessionId) {
        return new ResponseEntity(qrTokenService.startAttendance(sessionId),HttpStatus.OK);
    }

    @GetMapping("/current_attendance/{sessionId}")
    public ResponseEntity<QrTokenDto> getCurrentToken(@PathVariable Integer sessionId) {
        return new ResponseEntity(qrTokenService.getCurrentToken(sessionId),HttpStatus.OK);
    }

    @PostMapping("/stop_attendance/{sessionId}")
    public ResponseEntity<String> stopAttendance(@PathVariable Integer sessionId) {
        return new ResponseEntity(qrTokenService.stopAttendance(sessionId),HttpStatus.OK);
    }
}