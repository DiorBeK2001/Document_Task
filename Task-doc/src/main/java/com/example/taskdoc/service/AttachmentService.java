package com.example.taskdoc.service;

import com.example.taskdoc.model.domain.User;
import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentService {

    HttpEntity<?> uploadFileList(MultipartHttpServletRequest request, User user, String name);

    HttpEntity<?> byteFile(Long id);
}
