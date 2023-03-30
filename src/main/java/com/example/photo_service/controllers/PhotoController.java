package com.example.photo_service.controllers;

import com.example.photo_service.dto.GetPhotoResponseDto;
import com.example.photo_service.service.PhotoService;
import com.example.photo_service.service.impl.FTPDirectorySearcher;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photos")
    public ResponseEntity<List<GetPhotoResponseDto>> sendMessage() throws Exception {
        List<GetPhotoResponseDto> response = photoService.getPhotosWithFTP();
        return ResponseEntity.ok(response);
    }


}
