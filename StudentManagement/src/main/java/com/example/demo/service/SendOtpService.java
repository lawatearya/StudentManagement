package com.example.demo.service;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Otp;
import com.example.demo.interfaces.EmailService;
import com.example.demo.repository.OtpRepository;

@Service
public class SendOtpService {
	@Autowired
	OtpRepository otpRepository;

	@Autowired
	EmailService emailService;

	public String generateOtp() {
		return String.valueOf((int) (Math.random() * 900000) + 100000);

	}

	public void sendOtp(String email) {

		String otp = generateOtp();
		Optional<Otp> otpOptional = otpRepository.findByEmail(email);
		Otp otpEntity;
		if (otpOptional.isPresent()) {
			otpEntity = otpOptional.get();
		} else {
			otpEntity = new Otp();
			otpEntity.setEmail(email);
		}
		otpEntity.setOtp(otp);
		otpEntity.setExpiryTime(LocalDateTime.now().plusSeconds(90));
		otpRepository.save(otpEntity);
		emailService.sendEmail(email, "OTP Verification", "Your OTP is " + otp);

	}

	public String verifyOtp(String email, String otp) {
		Optional<Otp> otpOptional = otpRepository.findByEmail(email);

		if (otpOptional.isEmpty()) {
			return "OTP not found";
		}
		Otp otpEntity = otpOptional.get();

		if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
			return "OTP expired";
		}

		if (!otpEntity.getOtp().equals(otp)) {
			return "Invalid OTP";
		}
		otpEntity.setOtp(null);
		otpEntity.setExpiryTime(null);
		otpRepository.save(otpEntity);

		return "OTP verified successfully";
	}
}
