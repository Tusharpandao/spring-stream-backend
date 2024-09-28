package com.stream.app.controllers;

import com.stream.app.entity.Video;
import com.stream.app.service.VideoService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.stream.app.payload.CustomMessage;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/video")
@CrossOrigin("*")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/welcome")
    public String getMethodName(@RequestParam String param) {
        return param;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {

        Video video = new Video();

        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());


        Video savedVideo = videoService.save(video, file);
        if (savedVideo != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomMessage.builder()
                            .message("video not uploaded").success(false)
                            .build());
        }
    }


    @GetMapping("/")
    public List<Video> getAllVideos() {
        return  videoService.getAll();
    }

    @GetMapping("/stream/{videoId}")
    public ResponseEntity<Resource> stream(@PathVariable String videoId) {

        Video video = videoService.get(videoId);
        String contentType = video.getContentType();
        String filePath = video.getFilePath();
        Resource resource = new FileSystemResource(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);

    }

}
