package com.stream.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="yt_courses")
public class Course {
	@Id
	private Long id;
	
	private String title;
	
//	@OneToOne(mappedBy = "course")
//	private List<Video> list=new ArrayList<>();
	
}
