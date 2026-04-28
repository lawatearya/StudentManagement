package com.example.demo.dto;

import java.time.LocalDate;

public class EnrollmentDto {
	private LocalDate date;
	private String mode;
	private String status;
	private boolean VideoAccess;
	private double totalFees;
	private double paidFees;
	private double nextInstallment;
	private LocalDate dueDate;
	private LocalDate extendedDueDate;
	private long userId;
	private int batchId;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

		public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getVideoAccess() {
		return VideoAccess;
	}

	public void setVideoAccess(boolean videoAccess) {
		VideoAccess = videoAccess;
	}

	public double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(double totalFees) {
		this.totalFees = totalFees;
	}

	public double getPaidFees() {
		return paidFees;
	}

	public void setPaidFees(double paidFees) {
		this.paidFees = paidFees;
	}

	public double getNextInstallment() {
		return nextInstallment;
	}

	public void setNextInstallment(double nextInstallment) {
		this.nextInstallment = nextInstallment;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getExtendedDueDate() {
		return extendedDueDate;
	}

	public void setExtendedDueDate(LocalDate extendedDueDate) {
		this.extendedDueDate = extendedDueDate;
	}

	

	

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
