package com.example.photo_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetPhotoResponseDto {
    private String path;
    private LocalDateTime date;
    private Long size;

    public GetPhotoResponseDto(String path, LocalDateTime date, long size) {
        this.path = path;
        this.date = date;
        this.size = size;
    }
}
