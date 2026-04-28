package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionDto {
	private String topic;
	private LocalDate sessionDate;
	private LocalTime sessionTime;
	private int batchId;
	private long trainerId;

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

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}
}
