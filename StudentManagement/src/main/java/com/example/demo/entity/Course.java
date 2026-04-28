package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String courseName;
	private String courseDuration;
	private String syllabus_link;
	private String prerequsit;
	private String status;
	@OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	private List<Batch> batches;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getSyllabus_link() {
		return syllabus_link;
	}

	public void setSyllabus_link(String syllabus_link) {
		this.syllabus_link = syllabus_link;
	}

	public String getPrerequsit() {
		return prerequsit;
	}

	public void setPrerequsit(String prerequsit) {
		this.prerequsit = prerequsit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}


}
