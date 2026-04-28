package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String topic;
	private LocalDate sessionDate;
	private LocalTime sessionTime;
	private String status;
	private boolean attendanceStarted = false;

	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batch;

	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private Trainer trainer;

	@OneToOne(mappedBy = "session", cascade = CascadeType.ALL, optional = true)
	private SessionQrToken sessionQrToken;

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
	private List<Attendance> attendance;

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}

	public SessionQrToken getSessionQrToken() {
		return sessionQrToken;
	}

	public void setSessionQrToken(SessionQrToken sessionQrToken) {
		this.sessionQrToken = sessionQrToken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public LocalDate getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(LocalDate sessionDate) {
		this.sessionDate = sessionDate;
	}

	public LocalTime getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(LocalTime sessionTime) {
		this.sessionTime = sessionTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public boolean isAttendanceStarted() {
		return attendanceStarted;
	}

	public void setAttendanceStarted(boolean attendanceStarted) {
		this.attendanceStarted = attendanceStarted;
	}
}
