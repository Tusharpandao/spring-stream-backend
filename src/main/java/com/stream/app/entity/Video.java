package com.stream.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="yt_videos")
public class Video {
	
	@Id
	private String videoId;
	
	private String title;
	
	private String description;
	private String contentType;
	private String filePath;

//	@ManyToOne
//	private Course course;
	
	
	
}
