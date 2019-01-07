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

    private Path fileStorageLocation;

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

    /**
     * Burada fileid alarak yeni bir dinamik folder yarattık(Daha önce var ise
     * içine yazıyoruz tabii) ve resim dosyalarını bu şekilde raporların id'sine
     * göre grupladık.
     *
     * @param file
     * @param fileid
     * @return
     */
    public String storeFile(MultipartFile file, String fileid) {
        Path dynamicFileLocation = Paths.get(this.fileStorageLocation.toString() + "/" + fileid)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(dynamicFileLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }

        String fileName = file.getOriginalFilename();

        switch (file.getContentType()) {
            case "image/png": {
                break;
            }
            case "image/jpeg": {
                break;
            }
            case "image/jpg": {
                break;
            }
            case "image/gif": {
                break;
            }
            default: {
                throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". NOT ACCEPTABLE FILE TYPE!");
            }
        }
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + file.getOriginalFilename());
            }
            Path targetLocation = dynamicFileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileid, String fileName) {
        try {
            Path dynamicFileLocation = Paths.get(this.fileStorageLocation.toString() + "/" + fileid)
                    .toAbsolutePath().normalize();
            Path filePath = dynamicFileLocation.resolve(fileName).normalize();
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
