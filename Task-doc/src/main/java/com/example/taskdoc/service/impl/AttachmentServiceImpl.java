package com.example.taskdoc.service.impl;

import com.example.taskdoc.model.domain.Attachment;
import com.example.taskdoc.model.domain.User;
import com.example.taskdoc.model.vm.ResUploadFile;
import com.example.taskdoc.repository.AttachmentRepo;
import com.example.taskdoc.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.lang.module.ResolutionException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepo attachmentRepo;

    @Value("${upload.folder}")
    private String path;

    @Override
    public HttpEntity<?> uploadFileList(MultipartHttpServletRequest request, User user, String name) {
        try {
            Iterator<String> iterator = request.getFileNames();
            MultipartFile multipartFile;
            List<ResUploadFile> resUploadFiles = new ArrayList<>();
            while (iterator.hasNext()) {
                multipartFile = request.getFile(iterator.next());
                Attachment attachment = new Attachment();
                attachment.setSize(multipartFile.getSize());
                attachment.setUser(user);
                attachment.setName(name == null || "null".equals(name) ? multipartFile.getOriginalFilename() : name);
                attachment.setContentType(multipartFile.getContentType());
                if (getExt(multipartFile.getOriginalFilename()) == null || multipartFile.getSize() == 0 ||
                        !multipartFile.getContentType().startsWith("pdf") || !multipartFile.getContentType().startsWith("doc") ||
                        !multipartFile.getContentType().startsWith("docx")) {
                    log.error("error: {}", multipartFile.getContentType());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("You sent only pdf or doc or docx file");
                }
                long bytes = multipartFile.getSize();
                long kilobytes = (bytes / 1024);
                long megabytes = (kilobytes / 1024);
                if (megabytes > 1) {
                    log.error("your file more than 1 mb");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("error");
                }
                attachment.setExtension(getExt(multipartFile.getOriginalFilename()));
                Attachment savedAttachment = attachmentRepo.save(attachment);
                Calendar calendar = new GregorianCalendar();
                File uploadFolder = new File(
                        path + "/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" +
                                calendar.get(Calendar.DAY_OF_MONTH));
                if (uploadFolder.mkdirs() && uploadFolder.exists()) {
                    log.debug("package created: {}", uploadFolder.getAbsolutePath());
                }
                uploadFolder = uploadFolder.getAbsoluteFile();
                File file = new File(uploadFolder + "/" + savedAttachment.getId() + "_" + savedAttachment.getName());
                savedAttachment.setPath(file.getAbsolutePath());

                try {
                    multipartFile.transferTo(file);
                    resUploadFiles.add(
                            new ResUploadFile(savedAttachment.getId(), savedAttachment.getName(), savedAttachment.getContentType(),
                                    savedAttachment.getSize(), ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("api/attachment/byteFile")
                                    .path(attachment.getId().toString())
                                    .toUriString()));
                } catch (Exception e) {
                    attachmentRepo.delete(savedAttachment);
                    log.error("Error not saved: {}", e.getMessage());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("error, not saved");
                }
            }
            log.debug("File successfully saved: {} ", resUploadFiles);
            return ResponseEntity.ok(resUploadFiles);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error");
        }
    }

    @Override
    public HttpEntity<?> byteFile(Long id) {
        try {
            Attachment attachment = attachmentRepo.findById(id).orElseThrow(() -> new ResolutionException("getAttachmentID"));
            log.debug("success: {}", attachment);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + attachment.getName() + "\"")
                    .body(Files.readAllBytes(Paths.get(attachment.getPath())));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR");
        }
    }

    public String getExt(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf(".");
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot);
            }
        }
        return ext;
    }
}
