package com.example.photo_service.service;

import com.example.photo_service.dto.GetPhotoResponseDto;

import java.io.IOException;
import java.util.List;

public interface PhotoService {

    List<GetPhotoResponseDto> getPhotosWithFTP() throws IOException;
}
