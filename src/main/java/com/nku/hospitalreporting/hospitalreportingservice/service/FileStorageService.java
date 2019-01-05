package com.nku.hospitalreporting.hospitalreportingservice.service;

import com.nku.hospitalreporting.hospitalreportingservice.exception.FileStorageException;
import com.nku.hospitalreporting.hospitalreportingservice.exception.MyFileNotFoundException;
import com.nku.hospitalreporting.hospitalreportingservice.property.FileStorageProperties;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author serdar
 */
@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String uniqueId) {

        String fileName = uniqueId;
        
        switch (file.getContentType()) {
            case "image/png": {
                fileName += ".png";
                break;
            }
            case "image/jpeg": {
                fileName += ".jpeg";
                break;
            }
            case "image/jpg": {
                fileName += ".jpg";
                break;
            }
            case "image/gif": {
                fileName += ".gif";
                break;
            }
            default: {
                throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". NOT ACCEPTABLE FILE TYPE!");
            }
        }
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + file.getOriginalFilename());
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
