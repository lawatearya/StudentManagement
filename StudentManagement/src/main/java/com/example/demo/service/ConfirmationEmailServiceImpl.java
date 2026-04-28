package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Enrollment;
import com.example.demo.interfaces.EmailService;
import com.example.demo.repository.EnrollmentRepository;
@Service
public class ConfirmationEmailServiceImpl {
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	EmailService emailService;

	//@Scheduled(fixedRate = 60000)
	@Scheduled(cron = "0 0 9 * * ?")
	public void sendConfirmationEmails() {
		List<Enrollment> enrollements = enrollmentRepository.findByStatusAndConfEmail("Completed", false);
		for (Enrollment e : enrollements) {
			String to = e.getUser().getEmail();
			String name = e.getUser().getUsername();
			String subject = "Enrollement Confirmation";
			String body = "Hello " + name + " Your enrollment has been confirmed successfully." + " Thank you.";
			emailService.sendEmail(to, subject, body);
			e.setConfEmail(true);
			enrollmentRepository.save(e);
		}
	}
}
