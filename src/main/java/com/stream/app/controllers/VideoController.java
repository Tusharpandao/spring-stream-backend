package com.stream.app.controllers;

import com.stream.app.entity.Video;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.stream.app.payload.CustomMessage;
import com.stream.app.service.VideoService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/video")
@CrossOrigin("*")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {

        Video video = new Video();

        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());


       Video savedVideo= videoService.save(video,file);
       if (savedVideo != null){
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(video);
       }
       else {
           return ResponseEntity
                  .status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(CustomMessage.builder()
                          .message("video not uploaded")
                          .success(false)
                          .build());
       }
    }

    @GetMapping("/welcome")
    public String getMethodName(@RequestParam String param) {
        return param;
    }


}
