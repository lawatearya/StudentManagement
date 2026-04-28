package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private String mode;
	private String status;
	private boolean videoAccess;
	private double totalFees;
	private double reqFees;
	private double paidFees;
	private double nextInstallment;
	private LocalDate dueDate;
	private boolean confEmail = false;
	
	private LocalDate extendedDueDate;
	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private Users user;

	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batch batch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public boolean isVideoAccess() {
		return videoAccess;
	}

	public void setVideoAccess(boolean videoAccess) {
		this.videoAccess = videoAccess;
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

	public void setUser(Users user) {
		this.user = user;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public double getReqFees() {
		return reqFees;
	}

	public void setReqFees(double reqFees) {
		this.reqFees = reqFees;
	}

	public boolean isConfEmail() {
		return confEmail;
	}

	public void setConfEmail(boolean confEmail) {
		this.confEmail = confEmail;
	}

}
