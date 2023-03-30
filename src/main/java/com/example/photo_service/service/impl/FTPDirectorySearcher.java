package com.example.photo_service.service.impl;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.util.ArrayList;
import java.util.List;

public class FTPDirectorySearcher {

    // метод для поиска всех директорий с заданным именем в заданной директории
    public static List<String> findDirectoriesByName(String dirPath, String dirName, FTPClient ftpClient) throws Exception {
        List<String> dirs = new ArrayList<>();
        // переходим в заданную директорию
        ftpClient.changeWorkingDirectory(dirPath);
        // получаем список всех элементов в текущей директории
        FTPFile[] files = ftpClient.listDirectories();
        // проходим по всем элементам в текущей директории
        for (FTPFile file : files) {
            // если элемент - директория
            if (file.isDirectory()) {
                // если имя директории соответствует заданному
                if (file.getName().equals(dirName)) {
                    // добавляем путь к директории в список
                    dirs.add(dirPath + "/" + file.getName());
                } else {
                    // если имя директории не соответствует заданному,
                    // рекурсивно вызываем этот же метод для поиска директорий внутри текущей директории
                    List<String> subDirs = findDirectoriesByName(dirPath + "/" + file.getName(), dirName, ftpClient);
                    // добавляем все найденные директории в список
                    dirs.addAll(subDirs);
                }
            }
        }
        return dirs;
    }
}
