package com.example.demo.dto;

public class TrainerDto {
	private String name;
	private int exp;
	private String subject;
	private long userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getUserId() {
		return userId;
	}

	public void setUser_id(long userId) {
		this.userId = userId;
	}
}
