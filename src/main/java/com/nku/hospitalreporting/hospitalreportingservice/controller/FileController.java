package com.nku.hospitalreporting.hospitalreportingservice.controller;

import com.nku.hospitalreporting.hospitalreportingservice.payload.UniqueIdGenerator;
import com.nku.hospitalreporting.hospitalreportingservice.payload.UploadFileResponse;
import com.nku.hospitalreporting.hospitalreportingservice.service.FileStorageService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author serdar
 */
@RestController
public class FileController {
    
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload/{fileid}")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("fileid") String fileid) {
        String fileName = fileStorageService.storeFile(file,fileid);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/"+fileid+"/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultiple/{fileid}")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@PathVariable("fileid") String fileid) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,fileid))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileid}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileid,@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResource(fileid,fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
