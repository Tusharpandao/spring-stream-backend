package com.stream.app.service;

import com.stream.app.entity.Video;
import com.stream.app.repository.VideoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@Service
public class VideoService {

    @Value("${files.video}")
    String DIR;

    private VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    @PostConstruct
    public void init() {
        File file = new File(DIR);

        if (!file.exists()) {
            file.mkdir();
            System.out.println("Directory is created!");
        } else {
            System.out.println("Directory already exists!");
        }
    }


    public Video save(Video video, MultipartFile file) {
        try {
            // folder path :Create
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();


            // folder path with to the folder
            String cleanFileName = StringUtils.cleanPath(filename);
            String cleanFolder = StringUtils.cleanPath(DIR);

            Path path = Paths.get(cleanFolder, cleanFileName);

            System.out.println(path);

            // copy file to the folder
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            // video meta data
            video.setContentType(contentType);
            video.setFilePath(path.toString());

            // metadata save
            return videoRepository.save(video);

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }


    }


    public Video get(String videoId) {

        return videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
    }


    public Video getByTitle(String title) {
        return null;
    }


    public List<Video> getAll() {

        return videoRepository.findAll();
    }

}
