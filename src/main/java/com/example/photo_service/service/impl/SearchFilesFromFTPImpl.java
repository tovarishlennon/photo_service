package com.example.photo_service.service.impl;

import com.example.photo_service.dto.GetPhotoResponseDto;
import com.example.photo_service.service.SearchFileFromFTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static com.example.photo_service.util.ConvertCalendarToLocalDateUtil.toLocalDate;

@Service
public class SearchFilesFromFTPImpl implements SearchFileFromFTP {
    List<GetPhotoResponseDto> str = new ArrayList<>();
    @Value("${ftp.photos}")
    private String photos;
    @Value("${ftp.prefix}")
    private String prefix;
    @Value("${ftp.dot}")
    private String dot;
    @Value("${ftp.doubleDot}")
    private String doubleDot;
    @Override
    public List<GetPhotoResponseDto> search(FTPClient ftpClient, String remotePath) throws IOException {
        ftpClient.changeWorkingDirectory(remotePath);
        FTPFile[] directories = ftpClient.listFiles();
        for (FTPFile remoteFile : directories) {
            if (!remoteFile.getName().equals(dot) && !remoteFile.getName().equals(doubleDot)) {
                String remoteFilePath = remotePath + File.separator + remoteFile.getName();
                String name = remoteFile.getName();
                if (remoteFile.isDirectory() && name.equals(photos)) {
                    String rfp = remotePath + File.separator + remoteFile.getName();
                    ftpClient.changeWorkingDirectory(rfp);
                    FTPFile[] photos = ftpClient.listFiles();
                    for (FTPFile io : photos) {
                        if (io.isFile() && io.getName().contains(prefix)) {
                            str.add(new GetPhotoResponseDto(rfp + File.separator + io.getName(), toLocalDate(io.getTimestamp()), io.getSize()));
                        }
                    }
                } else if (remoteFile.isDirectory() && !name.equals(photos)) {
                    search(ftpClient, remoteFilePath);
                }
                else
                {
                    System.out.println("Found remote file " + remoteFilePath);
                }
            }
        }
        return str;
    }

}
