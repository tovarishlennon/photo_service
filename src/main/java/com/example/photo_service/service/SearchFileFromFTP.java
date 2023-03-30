package com.example.photo_service.service;

import com.example.photo_service.dto.GetPhotoResponseDto;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.List;

public interface SearchFileFromFTP {
    List<GetPhotoResponseDto> search(FTPClient ftpClient, String remotePath) throws IOException;
}
