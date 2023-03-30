package com.example.photo_service.client;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

@Service
public class FtpClientConnect {
    @Value("${ftp.ip}")
    private String ip;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.login}")
    private String login;

    @Value("${ftp.password}")
    private String password;

    private FTPClient ftp;

    public FTPClient openConnection() throws IOException {
        try {
            ftp = new FTPClient();
            ftp.setControlEncoding("UTF-8");

            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

            ftp.connect(ip, port);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new IOException("Exception in connecting to FTP Server");
            }
            ftp.login(login, password);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            return ftp;
        } catch (Exception ex) {
            throw new RemoteException("Cannot connect to the server!");
        }

    }
    public void close() throws IOException {
        ftp.logout();
        ftp.disconnect();
    }

}
