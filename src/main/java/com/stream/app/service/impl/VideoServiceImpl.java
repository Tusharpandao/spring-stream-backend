package com.stream.app.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.entity.Video;
import com.stream.app.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

	@Value("${files.video}")
	String DIR;

	@Override
	public Video save(Video video, MultipartFile file) {
		try {
			String filename = file.getOriginalFilename();
			String contentType = file.getContentType();
			InputStream inputStream = file.getInputStream();

			// folder path :Create

			String cleanFileName = StringUtils.cleanPath(filename);
			String cleanFolder=StringUtils.cleanPath(DIR);
			
			Path path = Paths.get(cleanFolder,cleanFileName);
			
			System.out.println(path);
			
			
			// folder path with to the folder

			// copy file to the folder

			// video mete data

			// metadata save

		} catch (IOException e) {
			
			e.printStackTrace();
		} 

		return null;
	}

	@Override
	public Video get(String videoId) {
		
		return null;
	}

	@Override
	public Video getByTitle(String title) {
		
		return null;
	}

	@Override
	public List<Video> getAll() {
		
		return null;
	}

}
