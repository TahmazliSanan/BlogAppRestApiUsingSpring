package com.project.blogapprestapi.services.impls;

import com.project.blogapprestapi.services.inters.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File name
        String fileName = file.getOriginalFilename();

        // Random file name
        String randomId = UUID.randomUUID().toString();
        String randomFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

        // Full path of file
        String fullPath = path + File.separator + randomFileName;

        // Create folder if it is not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        // Copy of file
        Files.copy(file.getInputStream(), Paths.get(fullPath));
        return randomFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}