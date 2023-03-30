package com.example.photo_service.service.impl;

import com.example.photo_service.client.FtpClientConnect;
import com.example.photo_service.dto.GetPhotoResponseDto;
import com.example.photo_service.service.PhotoService;
import com.example.photo_service.service.SearchFileFromFTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final FtpClientConnect ftpClientConnect;
    private final SearchFileFromFTP searchFileFromFTP;

    public PhotoServiceImpl(FtpClientConnect ftpClientConnect, SearchFileFromFTP searchFileFromFTP) {
        this.ftpClientConnect = ftpClientConnect;
        this.searchFileFromFTP = searchFileFromFTP;
    }

    @Override
    public List<GetPhotoResponseDto> getPhotosWithFTP() throws IOException {
        try {
            FTPClient ftpClient = ftpClientConnect.openConnection();
            return searchFileFromFTP.search(ftpClient, File.separator);
        } finally {
            ftpClientConnect.close();
        }
    }
}
